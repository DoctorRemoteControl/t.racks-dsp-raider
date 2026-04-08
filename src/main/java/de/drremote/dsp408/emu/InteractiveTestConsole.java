package de.drremote.dsp408.emu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

final class InteractiveTestConsole implements Runnable {
    private final MeterEngine meterEngine;

    InteractiveTestConsole(MeterEngine meterEngine) {
        this.meterEngine = meterEngine;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            printBanner();
            printPrompt();

            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    printPrompt();
                    continue;
                }

                try {
                    if (handle(trimmed)) {
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println("[TEST] error: " + ex.getMessage());
                }

                printPrompt();
            }
        } catch (IOException e) {
            System.out.println("[TEST] console stopped: " + e.getMessage());
        }
    }

    private boolean handle(String line) {
        String[] parts = line.trim().split("\\s+");
        String cmd = parts[0].toLowerCase(Locale.ROOT);

        switch (cmd) {
            case "help", "?" -> printHelp();

            case "show", "status" -> {
                System.out.println(meterEngine.configSummary());
                System.out.println(meterEngine.debugState().summary());
            }

            case "set", "const" -> {
                requireArgCount(parts, 5, "set <slot> <b0> <b1> <b2>");
                int slot = parseInt(parts[1]);
                int b0 = parseByte(parts[2]);
                int b1 = parseByte(parts[3]);
                int b2 = parseByte(parts[4]);

                meterEngine.setConstantTriplet(slot, b0, b1, b2);
                System.out.println("[TEST] constant set");
                System.out.println(meterEngine.configSummary());
            }

            case "zero" -> {
                meterEngine.setZero();
                System.out.println("[TEST] mode set to ZERO");
                System.out.println(meterEngine.configSummary());
            }

            case "nan" -> {
                requireArgCount(parts, 2, "nan <slot> [peak]");
                int slot = parseInt(parts[1]);
                int peak = parts.length >= 3 ? parseByte(parts[2]) : 0x00;

                meterEngine.setNanSlot(slot, peak);
                System.out.println("[TEST] mode set to NAN_SLOT");
                System.out.println(meterEngine.configSummary());
            }

            case "ramp" -> {
                requireArgCount(parts, 3, "ramp <slot> <byteIndex> [step]");
                int slot = parseInt(parts[1]);
                int byteIndex = parseInt(parts[2]);
                int step = parts.length >= 4 ? parseInt(parts[3]) : 8;

                meterEngine.setSingleRamp(slot, byteIndex, step);
                System.out.println("[TEST] mode set to SINGLE_SLOT_RAMP");
                System.out.println(meterEngine.configSummary());
            }

            case "sweep" -> {
                requireArgCount(parts, 2, "sweep <byteIndex> [step] [hold]");
                int byteIndex = parseInt(parts[1]);
                int step = parts.length >= 3 ? parseInt(parts[2]) : 8;
                int hold = parts.length >= 4 ? parseInt(parts[3]) : 8;

                meterEngine.setSweep(byteIndex, step, hold);
                System.out.println("[TEST] mode set to SLOT_SWEEP");
                System.out.println(meterEngine.configSummary());
            }

            case "scan" -> {
                requireArgCount(parts, 4, "scan <low> <high> <peak> [hold]");
                int low = parseByte(parts[1]);
                int high = parseByte(parts[2]);
                int peak = parseByte(parts[3]);
                int hold = parts.length >= 5 ? parseInt(parts[4]) : 8;

                meterEngine.setSlotScan(low, high, peak, hold);
                System.out.println("[TEST] mode set to FLOAT16_SLOT_SCAN");
                System.out.println(meterEngine.configSummary());
            }

            case "endian" -> {
                requireArgCount(parts, 5, "endian <slot> <low> <high> <peak> [hold]");
                int slot = parseInt(parts[1]);
                int low = parseByte(parts[2]);
                int high = parseByte(parts[3]);
                int peak = parseByte(parts[4]);
                int hold = parts.length >= 6 ? parseInt(parts[5]) : 8;

                meterEngine.setEndianToggle(slot, low, high, peak, hold);
                System.out.println("[TEST] mode set to FLOAT16_ENDIAN_TOGGLE");
                System.out.println(meterEngine.configSummary());
            }

            case "peakramp" -> {
                requireArgCount(parts, 4, "peakramp <slot> <low> <high> [step]");
                int slot = parseInt(parts[1]);
                int low = parseByte(parts[2]);
                int high = parseByte(parts[3]);
                int step = parts.length >= 5 ? parseInt(parts[4]) : 8;

                meterEngine.setPeakRamp(slot, low, high, step);
                System.out.println("[TEST] mode set to PEAK_RAMP");
                System.out.println(meterEngine.configSummary());
            }

            case "flags" -> {
                requireArgCount(parts, 3, "flags <b40> <b41>");
                int b40 = parseByte(parts[1]);
                int b41 = parseByte(parts[2]);

                meterEngine.setStatusBytes(b40, b41);
                System.out.println("[TEST] status bytes set");
                System.out.println(meterEngine.configSummary());
            }

            case "quit", "exit" -> {
                System.out.println("[TEST] stopping emulator");
                System.exit(0);
                return true;
            }
            case "f16ramp" -> {
                requireArgCount(parts, 5, "f16ramp <slot> <low> <startHigh> <endHigh> [peak] [hold]");
                int slot = parseInt(parts[1]);
                int low = parseByte(parts[2]);
                int startHigh = parseByte(parts[3]);
                int endHigh = parseByte(parts[4]);
                int peak = parts.length >= 6 ? parseByte(parts[5]) : 0x00;
                int hold = parts.length >= 7 ? parseInt(parts[6]) : 8;

                meterEngine.setFloatHighRamp(slot, low, startHigh, endHigh, peak, hold);
                System.out.println("[TEST] mode set to FLOAT16_HIGH_RAMP");
                System.out.println(meterEngine.configSummary());
            }

            default -> {
                System.out.println("[TEST] unknown command: " + parts[0]);
                System.out.println("[TEST] type 'help' for available commands");
            }
        }

        return false;
    }

    private static void printBanner() {
        System.out.println("[TEST] interactive console started");
        System.out.println("[TEST] type 'help' for commands");
    }

    private static void printHelp() {
        System.out.println("[TEST] commands:");
        System.out.println("  help");
        System.out.println("  show");
        System.out.println("  set <slot> <b0> <b1> <b2>");
        System.out.println("  const <slot> <b0> <b1> <b2>");
        System.out.println("  zero");
        System.out.println("  nan <slot> [peak]");
        System.out.println("  ramp <slot> <byteIndex> [step]");
        System.out.println("  sweep <byteIndex> [step] [hold]");
        System.out.println("  scan <low> <high> <peak> [hold]");
        System.out.println("  endian <slot> <low> <high> <peak> [hold]");
        System.out.println("  peakramp <slot> <low> <high> [step]");
        System.out.println("  flags <b40> <b41>");
        System.out.println("  quit");
        System.out.println("[TEST] bytes may be entered as FF, 0xFF, or decimal");
    }

    private static void printPrompt() {
        System.out.print("test> ");
        System.out.flush();
    }

    private static void requireArgCount(String[] parts, int minArgs, String syntax) {
        if (parts.length < minArgs) {
            throw new IllegalArgumentException("usage: " + syntax);
        }
    }

    private static int parseInt(String text) {
        String value = text.trim();
        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        }
        return Integer.parseInt(value);
    }

    private static int parseByte(String text) {
        String value = text.trim();

        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        }

        if (value.matches("[0-9A-Fa-f]{1,2}") && value.matches(".*[A-Fa-f].*")) {
            return Integer.parseInt(value, 16);
        }

        if (value.matches("[0-9A-Fa-f]{2}")) {
            return Integer.parseInt(value, 16);
        }

        return Integer.parseInt(value);
    }
}
