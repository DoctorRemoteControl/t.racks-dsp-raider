package de.drremote.dsp408.emu;

record EmulatorConfig(
        String listenHost,
        int listenPort,
        int socketReadTimeoutMs,
        MeterMode meterMode,
        int activeSlot,
        int focusByte,
        int rampStep,
        int slotHoldPolls,
        int meterLowByte,
        int meterHighByte,
        int meterPeakByte,
        boolean verbose,
        boolean testConsole,
        boolean interactive,
        boolean gui
) {
    static EmulatorConfig fromSystemProperties() {
        boolean testConsole = Boolean.parseBoolean(
                System.getProperty("emu.testConsole", "false")
        );

        boolean interactive = Boolean.parseBoolean(
                System.getProperty("emu.interactive", "false")
        );

        boolean gui = Boolean.parseBoolean(
                System.getProperty("emu.gui", "false")
        );

        boolean verbose = Boolean.parseBoolean(
                System.getProperty("emu.verbose", Boolean.toString(!testConsole))
        );

        return new EmulatorConfig(
                System.getProperty("emu.host", "127.0.0.1"),
                Integer.getInteger("emu.port", 9761),
                Integer.getInteger("emu.readTimeoutMs", 600000),
                MeterMode.fromString(System.getProperty("meter.mode", "SINGLE_SLOT_RAMP")),
                clamp(parseFlexibleInt(System.getProperty("meter.slot"), 0), 0, 11),
                clamp(parseFlexibleInt(System.getProperty("meter.byteIndex"), 0), 0, 2),
                clamp(parseFlexibleInt(System.getProperty("meter.step"), 8), 1, 255),
                clamp(parseFlexibleInt(System.getProperty("meter.slotHoldPolls"), 8), 1, 1000),
                clamp(parseFlexibleInt(System.getProperty("meter.low"), 0), 0, 255),
                clamp(parseFlexibleInt(System.getProperty("meter.high"), 0), 0, 255),
                clamp(parseFlexibleInt(System.getProperty("meter.peak"), 0), 0, 255),
                verbose,
                testConsole,
                interactive,
                gui
        );
    }

    private static int parseFlexibleInt(String text, int defaultValue) {
        if (text == null || text.isBlank()) {
            return defaultValue;
        }

        String value = text.trim();
        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        }
        return Integer.parseInt(value);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}