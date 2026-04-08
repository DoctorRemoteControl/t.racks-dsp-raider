package de.drremote.dsp408.emu;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public final class Dsp408MeterEmulatorMain {
    private Dsp408MeterEmulatorMain() {
    }

    public static void main(String[] args) throws IOException {
        EmulatorConfig config = EmulatorConfig.fromSystemProperties();
        StaticProtocolData staticProtocolData = new StaticProtocolData();
        MeterEngine meterEngine = new MeterEngine(config);

        if (config.interactive()) {
            Thread consoleThread = new Thread(
                    new InteractiveConsole(meterEngine),
                    "dsp408-interactive-console"
            );
            consoleThread.setDaemon(true);
            consoleThread.start();
        }

        if (config.gui()) {
            SwingUtilities.invokeLater(() -> new MeterGuiFrame(meterEngine).setVisible(true));
        }

        try (ServerSocket serverSocket = new ServerSocket(
                config.listenPort(),
                50,
                InetAddress.getByName(config.listenHost()))) {

            System.out.printf(
                    "DSP408 meter emulator listening on %s:%d | mode=%s slot=%d byte=%d step=%d hold=%d low=0x%02X high=0x%02X peak=0x%02X verbose=%s testConsole=%s interactive=%s gui=%s%n",
                    config.listenHost(),
                    config.listenPort(),
                    config.meterMode(),
                    config.activeSlot(),
                    config.focusByte(),
                    config.rampStep(),
                    config.slotHoldPolls(),
                    config.meterLowByte(),
                    config.meterHighByte(),
                    config.meterPeakByte(),
                    config.verbose(),
                    config.testConsole(),
                    config.interactive(),
                    config.gui()
            );

            if (config.testConsole() && !config.verbose()) {
                System.out.println("[TEST] test console active; protocol spam is suppressed");
            }

            while (true) {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(config.socketReadTimeoutMs());

                if (config.verbose()) {
                    System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                } else if (config.testConsole()) {
                    System.out.println("[TEST] client connected: " + socket.getRemoteSocketAddress());
                }

                new Thread(
                        new ClientSession(
                                socket,
                                staticProtocolData,
                                meterEngine,
                                config.verbose(),
                                config.testConsole()
                        ),
                        "dsp408-client-session"
                ).start();
            }
        }
    }
}