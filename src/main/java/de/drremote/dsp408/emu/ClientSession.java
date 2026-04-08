package de.drremote.dsp408.emu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

final class ClientSession implements Runnable {
    private final Socket socket;
    private final StaticProtocolData staticProtocolData;
    private final MeterEngine meterEngine;
    private final boolean verbose;
    private final boolean testConsole;

    private long lastPrintedConfigVersion = -1L;

    ClientSession(
            Socket socket,
            StaticProtocolData staticProtocolData,
            MeterEngine meterEngine,
            boolean verbose,
            boolean testConsole
    ) {
        this.socket = socket;
        this.staticProtocolData = staticProtocolData;
        this.meterEngine = meterEngine;
        this.verbose = verbose;
        this.testConsole = testConsole;
    }

    @Override
    public void run() {
        try (socket;
             BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())) {

            while (true) {
                Dsp408Frame request = Dsp408Frame.read(in);
                int cmd = request.command();

                if (verbose) {
                    System.out.printf("RX cmd=0x%02X payload=%s%n", cmd, HexUtil.toHex(request.payload()));
                }

                if (cmd == 0x11) {
                    if (verbose) {
                        System.out.println("Client requested close (0x11)");
                    } else if (testConsole) {
                        System.out.println("[TEST] client requested close");
                    }
                    break;
                }

                byte[] responsePayload = (cmd == 0x40)
                        ? meterEngine.nextMeterPayload()
                        : staticProtocolData.buildResponse(request);

                if (responsePayload == null) {
                    if (verbose) {
                        System.out.printf("No response defined for cmd=0x%02X%n", cmd);
                    }
                    continue;
                }

                Dsp408Frame response = new Dsp408Frame(responsePayload);
                byte[] frameBytes = response.encode();
                out.write(frameBytes);
                out.flush();

                if (verbose) {
                    System.out.printf("TX cmd=0x%02X payload=%s%n", response.command(), HexUtil.toHex(responsePayload));
                } else if (testConsole && cmd == 0x40) {
                    printTestSummaryIfConfigChanged();
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Client timed out: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("Client disconnected: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Session error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    private void printTestSummaryIfConfigChanged() {
        long currentVersion = meterEngine.configVersion();
        if (currentVersion == lastPrintedConfigVersion) {
            return;
        }

        System.out.println(meterEngine.currentSummary());
        lastPrintedConfigVersion = currentVersion;
    }
}