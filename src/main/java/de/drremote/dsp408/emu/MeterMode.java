package de.drremote.dsp408.emu;

enum MeterMode {
    ZERO,
    SINGLE_SLOT_RAMP,
    SLOT_SWEEP,

    FLOAT16_SLOT_CONSTANT,
    FLOAT16_ENDIAN_TOGGLE,
    FLOAT16_SLOT_SCAN,
    FLOAT16_HIGH_RAMP,
    PEAK_RAMP,
    NAN_SLOT,

    MANUAL_GUI;

    static MeterMode fromString(String text) {
        if (text == null || text.isBlank()) {
            return SINGLE_SLOT_RAMP;
        }

        return switch (text.trim().toUpperCase(java.util.Locale.ROOT)) {
            case "ZERO" -> ZERO;

            case "SINGLE_SLOT_RAMP", "SINGLE", "RAMP" -> SINGLE_SLOT_RAMP;
            case "SLOT_SWEEP", "SWEEP" -> SLOT_SWEEP;

            case "FLOAT16_SLOT_CONSTANT", "FLOAT16", "F16", "CONST", "F16CONST",
                 "BYTE_SLOT_CONSTANT", "BYTECONST", "BYTESLOT", "BYTE" -> FLOAT16_SLOT_CONSTANT;

            case "FLOAT16_ENDIAN_TOGGLE", "ENDIAN", "TOGGLE", "SWAP", "F16TOGGLE" -> FLOAT16_ENDIAN_TOGGLE;
            case "FLOAT16_SLOT_SCAN", "F16SCAN", "FLOAT16SCAN", "BYTE_SLOT_SCAN", "BYTESCAN" -> FLOAT16_SLOT_SCAN;
            case "FLOAT16_HIGH_RAMP", "F16RAMP", "HIGHRAMP", "INARAMP" -> FLOAT16_HIGH_RAMP;

            case "PEAK_RAMP", "PEAK" -> PEAK_RAMP;
            case "NAN_SLOT", "NAN" -> NAN_SLOT;

            case "MANUAL_GUI", "GUI" -> MANUAL_GUI;

            default -> throw new IllegalArgumentException("Unknown meter.mode: " + text);
        };
    }
}