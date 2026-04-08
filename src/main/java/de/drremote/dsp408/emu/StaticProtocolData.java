package de.drremote.dsp408.emu;

final class StaticProtocolData {
    private static final String[] PRESET_NAMES = {
            "decoderPres   ",
            "pres2         ",
            "pres3         ",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset",
            "Default Preset"
    };

    private static final byte[] HANDSHAKE_INIT_RESPONSE = HexUtil.parseHex("01 00 02 10 13");
    private static final byte[] DEVICE_INFO_RESPONSE = HexUtil.parseHex("01 00 0E 13 44 53 50 34 30 38 20 56 30 31 30 34 20");
    private static final byte[] SYSTEM_INFO_RESPONSE = HexUtil.parseHex("01 00 07 2C 00 27 0F 00 00 00");
    private static final byte[] QUERY_22_RESPONSE = HexUtil.parseHex("01 00 15 22 FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00");
    private static final byte[] QUERY_14_RESPONSE = HexUtil.parseHex("01 00 02 14 01");
    private static final byte[] KEEPALIVE_RESPONSE = HexUtil.parseHex("01 00 01 01");

    private final BlockDumpRepository blockDumpRepository = new BlockDumpRepository();

    byte[] buildResponse(Dsp408Frame request) {
        int cmd = request.command();
        return switch (cmd) {
            case 0x10 -> HANDSHAKE_INIT_RESPONSE.clone();
            case 0x13 -> DEVICE_INFO_RESPONSE.clone();
            case 0x2C -> SYSTEM_INFO_RESPONSE.clone();
            case 0x22 -> QUERY_22_RESPONSE.clone();
            case 0x14 -> QUERY_14_RESPONSE.clone();
            case 0x12 -> KEEPALIVE_RESPONSE.clone();
            case 0x29 -> buildPresetNameResponse(request.payload());
            case 0x27 -> buildBlockResponse(request.payload());
            default -> null;
        };
    }

    private byte[] buildPresetNameResponse(byte[] requestPayload) {
        if (requestPayload.length < 5) {
            return null;
        }
        int presetIndex = requestPayload[4] & 0xFF;
        if (presetIndex < 0 || presetIndex >= PRESET_NAMES.length) {
            return null;
        }
        byte[] payload = new byte[19];
        payload[0] = 0x01;
        payload[1] = 0x00;
        payload[2] = 0x10;
        payload[3] = 0x29;
        payload[4] = (byte) presetIndex;
        HexUtil.putAscii(payload, 5, 14, PRESET_NAMES[presetIndex]);
        return payload;
    }

    private byte[] buildBlockResponse(byte[] requestPayload) {
        if (requestPayload.length < 5) {
            return null;
        }
        int blockIndex = requestPayload[4] & 0xFF;
        return blockDumpRepository.buildBlockResponse(blockIndex);
    }
}
