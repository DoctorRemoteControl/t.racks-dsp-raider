package de.drremote.dsp408.emu;

record MeterDebugState(
        String mode,
        int slot,
        int byte0,
        int byte1,
        int byte2,
        long poll
) {
    String summary() {
        if (slot < 0) {
            return String.format(
                    "[TEST] mode=%s poll=%d bytes=%s",
                    mode,
                    poll,
                    tripletHex()
            );
        }

        return String.format(
                "[TEST] mode=%s poll=%d slot=%d bytes=%s",
                mode,
                poll,
                slot,
                tripletHex()
        );
    }

    String stableKey() {
        return mode + "|" + slot + "|" + byte0 + "|" + byte1 + "|" + byte2;
    }

    private String tripletHex() {
        return String.format("%02X %02X %02X", byte0 & 0xFF, byte1 & 0xFF, byte2 & 0xFF);
    }
}