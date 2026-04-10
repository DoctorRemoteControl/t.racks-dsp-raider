package de.drremote.dsp408.tool;

import de.drremote.dsp408.decode.DspLibLookup;
import de.drremote.dsp408.decode.WriteDecodeResult;
import de.drremote.dsp408.decode.WriteDecodeService;
import de.drremote.dsp408.model.SniffedFrame;
import de.drremote.dsp408.proxy.GuiSnifferClient;
import de.drremote.dsp408.proxy.ProxyConstants;

import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class DspGuiWriteDecodeCli {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss");

    private DspGuiWriteDecodeCli() {
    }

    public static void main(String[] args) {
        Options options;
        try {
            options = Options.parse(args);
        } catch (IllegalArgumentException ex) {
            System.err.println("Error: " + ex.getMessage());
            System.err.println();
            printUsage();
            System.exit(2);
            return;
        }

        if (options.help) {
            printUsage();
            return;
        }

        try {
            DspLibLookup lib = DspLibLookup.load(options.libPath);
            WriteDecodeService decoder = new WriteDecodeService(lib);

            try (GuiSnifferClient sniffer = new GuiSnifferClient(
                    options.streamHost,
                    options.streamPort,
                    options.verbose)) {
                sniffer.connect();

                System.out.println("DSP GUI Write Decoder");
                System.out.println("Lib    : " + options.libPath.toAbsolutePath());
                System.out.println("Stream : " + options.streamHost + ":" + options.streamPort);
                System.out.println("Mode   : live");
                System.out.println("Hint   : output crossover uses raw 0x00 for bypass and 0x01..0x14 for slope/family");
                System.out.println();

                while (true) {
                    SniffedFrame frame = sniffer.waitForAnyFrame(60_000);
                    if (frame == null || !frame.isWrite()) {
                        continue;
                    }
                    if (options.ignoreKeepalive && frame.command() != null && frame.command() == 0x40) {
                        continue;
                    }

                    WriteDecodeResult decoded;
                    try {
                        decoded = decoder.decode(frame);
                    } catch (Exception ex) {
                        decoded = new WriteDecodeResult(
                                false,
                                frame.commandHex(),
                                null,
                                null,
                                "decode_error: " + ex.getMessage(),
                                List.of("payload = " + frame.payloadHex())
                        );
                    }

                    System.out.println(formatLiveEntry(frame, decoded));
                    System.out.println();
                }
            }
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("DSP GUI Write Decode CLI");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java -jar tracks-dsp-gui-write-decode-all.jar --lib DspLib-408.json");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  --lib <path>               Path to DspLib JSON");
        System.out.println("  --stream-host <host>       Stream host (default: 127.0.0.1)");
        System.out.println("  --stream-port <port>       Stream port (default: 19081)");
        System.out.println("  --show-0x40                Do not ignore cmd 0x40");
        System.out.println("  --verbose                  More logs");
        System.out.println("  --help                     Help");
    }

    private static String formatLiveEntry(SniffedFrame frame, WriteDecodeResult decoded) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(LocalTime.now().format(TS))
                .append("] ")
                .append(decoded.commandHex())
                .append("  ")
                .append(decoded.headline());

        if (decoded.status() != null) {
            sb.append("\n  status  : ").append(decoded.status());
        }
        sb.append("\n  payload : ").append(frame.payloadHex());

        if (decoded.parameterPath() != null) {
            sb.append("\n  path    : ").append(decoded.parameterPath());
        }
        if (decoded.details() != null) {
            for (String detail : decoded.details()) {
                sb.append("\n  ").append(detail);
            }
        }
        return sb.toString();
    }

    private static final class Options {
        private Path libPath = Path.of("DspLib-408.json");
        private String streamHost = ProxyConstants.DEFAULT_STREAM_HOST;
        private int streamPort = ProxyConstants.DEFAULT_STREAM_PORT;
        private boolean ignoreKeepalive = true;
        private boolean verbose;
        private boolean help;

        private static Options parse(String[] args) {
            Options options = new Options();

            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                switch (arg) {
                    case "--lib" -> options.libPath = Path.of(requireValue(args, ++i, "--lib"));
                    case "--stream-host" -> options.streamHost = requireValue(args, ++i, "--stream-host");
                    case "--stream-port" -> options.streamPort = Integer.parseInt(requireValue(args, ++i, "--stream-port"));
                    case "--show-0x40" -> options.ignoreKeepalive = false;
                    case "--verbose" -> options.verbose = true;
                    case "--help", "-h" -> options.help = true;
                    default -> throw new IllegalArgumentException("Unknown option: " + arg);
                }
            }

            return options;
        }

        private static String requireValue(String[] args, int index, String option) {
            if (index >= args.length) {
                throw new IllegalArgumentException("Missing value for " + option);
            }
            return args[index];
        }
    }
}
