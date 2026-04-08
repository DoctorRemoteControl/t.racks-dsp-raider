package de.drremote.dsp408.emu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

final class InteractiveConsole implements Runnable {
    private final MeterEngine meterEngine;

    InteractiveConsole(MeterEngine meterEngine) {
        this.meterEngine = meterEngine;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            printHelp();
            System.out.println(meterEngine.currentSummary());

            while (true) {
                System.out.print("test> ");
                System.out.flush();

                String line = reader.readLine();
                if (line == null) {
                    return;
                }

                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    if (handle(line)) {
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("[TEST] error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("[TEST] console stopped: " + e.getMessage());
        }
    }

    private boolean handle(String line) {
        String[] p = line.split("\\s+");
        String cmd = p[0].toLowerCase(java.util.Locale.ROOT);

        switch (cmd) {
            case "help", "h", "?" -> printHelp();

            case "show", "status" -> System.out.println(meterEngine.currentSummary());

            case "set" -> {
                requireArgs(p, 5, "set <slot> <b0> <b1> <b2>");
                meterEngine.setConstant(
                        parseInt(p[1]),
                        parseByte(p[2]),
                        parseByte(p[3]),
                        parseByte(p[4])
                );
                System.out.println("[TEST] constant set");
                System.out.println(meterEngine.currentSummary());
            }

            case "zero" -> {
                meterEngine.setZero();
                System.out.println("[TEST] zero set");
                System.out.println(meterEngine.currentSummary());
            }

            case "ramp" -> {
                requireArgs(p, 4, "ramp <slot> <byteIndex> <step>");
                meterEngine.setRamp(
                        parseInt(p[1]),
                        parseInt(p[2]),
                        parseInt(p[3])
                );
                System.out.println("[TEST] ramp set");
                System.out.println(meterEngine.currentSummary());
            }

            case "sweep" -> {
                requireArgs(p, 4, "sweep <byteIndex> <step> <hold>");
                meterEngine.setSweep(
                        parseInt(p[1]),
                        parseInt(p[2]),
                        parseInt(p[3])
                );
                System.out.println("[TEST] sweep set");
                System.out.println(meterEngine.currentSummary());
            }

            case "scan" -> {
                requireArgs(p, 4, "scan <b0> <b1> <b2> [hold]");
                int hold = p.length >= 5 ? parseInt(p[4]) : 8;
                meterEngine.setScan(
                        parseByte(p[1]),
                        parseByte(p[2]),
                        parseByte(p[3]),
                        hold
                );
                System.out.println("[TEST] scan set");
                System.out.println(meterEngine.currentSummary());
            }

            case "endian" -> {
                requireArgs(p, 5, "endian <slot> <b0> <b1> <b2> [hold]");
                int hold = p.length >= 6 ? parseInt(p[5]) : 8;
                meterEngine.setEndianToggle(
                        parseInt(p[1]),
                        parseByte(p[2]),
                        parseByte(p[3]),
                        parseByte(p[4]),
                        hold
                );
                System.out.println("[TEST] endian toggle set");
                System.out.println(meterEngine.currentSummary());
            }

            case "peakramp" -> {
                requireArgs(p, 5, "peakramp <slot> <b0> <b1> <step>");
                meterEngine.setPeakRamp(
                        parseInt(p[1]),
                        parseByte(p[2]),
                        parseByte(p[3]),
                        parseInt(p[4])
                );
                System.out.println("[TEST] peak ramp set");
                System.out.println(meterEngine.currentSummary());
            }

            case "nan" -> {
                requireArgs(p, 2, "nan <slot> [peak]");
                int peak = p.length >= 3 ? parseByte(p[2]) : 0x00;
                meterEngine.setNan(parseInt(p[1]), peak);
                System.out.println("[TEST] nan set");
                System.out.println(meterEngine.currentSummary());
            }

            case "flags" -> {
                requireArgs(p, 3, "flags <b40> <b41>");
                meterEngine.setStatusBytes(
                        parseByte(p[1]),
                        parseByte(p[2])
                );
                System.out.println("[TEST] status bytes set");
                System.out.println(meterEngine.currentSummary());
            }

            case "quit", "exit", "stop" -> {
                System.out.println("[TEST] stopping emulator");
                System.exit(0);
                return true;
            }
            
            case "f16ramp" -> {
                requireArgs(p, 5, "f16ramp <slot> <low> <startHigh> <endHigh> [peak] [hold]");
                int peak = p.length >= 6 ? parseByte(p[5]) : 0x00;
                int hold = p.length >= 7 ? parseInt(p[6]) : 8;

                meterEngine.setFloatHighRamp(
                        parseInt(p[1]),
                        parseByte(p[2]),
                        parseByte(p[3]),
                        parseByte(p[4]),
                        peak,
                        hold
                );
                System.out.println("[TEST] float16 high ramp set");
                System.out.println(meterEngine.currentSummary());
            }

            default -> System.out.println("[TEST] unknown command: " + cmd);
        }

        return false;
    }

    private static void printHelp() {
        System.out.println("[TEST] interactive commands:");
        System.out.println("  show");
        System.out.println("  set <slot> <b0> <b1> <b2>");
        System.out.println("  zero");
        System.out.println("  ramp <slot> <byteIndex> <step>");
        System.out.println("  sweep <byteIndex> <step> <hold>");
        System.out.println("  scan <b0> <b1> <b2> [hold]");
        System.out.println("  endian <slot> <b0> <b1> <b2> [hold]");
        System.out.println("  peakramp <slot> <b0> <b1> <step>");
        System.out.println("  nan <slot> [peak]");
        System.out.println("  flags <b40> <b41>");
        System.out.println("  f16ramp <slot> <low> <startHigh> <endHigh> [peak] [hold]");
        System.out.println("  quit");
    }

    private static void requireArgs(String[] args, int minLength, String syntax) {
        if (args.length < minLength) {
            throw new IllegalArgumentException("usage: " + syntax);
        }
    }

    private static int parseByte(String text) {
        int value = parseFlexibleInt(text);
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("byte out of range: " + text);
        }
        return value;
    }

    private static int parseInt(String text) {
        return parseFlexibleInt(text);
    }

    private static int parseFlexibleInt(String text) {
        String value = text.trim();
        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        }
        if (value.matches("[0-9A-Fa-f]{2}")) {
            return Integer.parseInt(value, 16);
        }
        return Integer.parseInt(value);
    }
}
