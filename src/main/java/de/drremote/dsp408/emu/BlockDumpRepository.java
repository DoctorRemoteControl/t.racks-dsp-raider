package de.drremote.dsp408.emu;

final class BlockDumpRepository {
    private final byte[][] blocks;

    BlockDumpRepository() {
        this.blocks = new byte[29][];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new byte[i == 28 ? 48 : 50];
        }
        seedNames();
    }

    byte[] buildBlockResponse(int blockIndex) {
        if (blockIndex < 0 || blockIndex >= blocks.length) {
            return null;
        }
        byte[] block = blocks[blockIndex];
        int logicalLen = 1 + 1 + block.length;
        byte[] payload = new byte[5 + block.length];
        payload[0] = 0x01;
        payload[1] = 0x00;
        payload[2] = (byte) logicalLen;
        payload[3] = 0x24;
        payload[4] = (byte) blockIndex;
        System.arraycopy(block, 0, payload, 5, block.length);
        return payload;
    }

    private void seedNames() {
        HexUtil.putAscii(blocks[0], 2, 14, "decoderPres   ");

        putName(0x00, 0x10, "InA");
        putName(0x03, 0x06, "InB");
        putName(0x05, 0x2E, "InC");
        putName(0x08, 0x24, "InD");
        putName(0x0B, 0x1A, "Out1");
        putName(0x0D, 0x1E, "Out2");
        putName(0x0F, 0x22, "Out3");
        putName(0x11, 0x26, "Out4");
        putName(0x13, 0x2A, "Out5");
        putName(0x15, 0x2E, "Out6");
        putName(0x18, 0x00, "Out7");
        putName(0x1A, 0x04, "Out8");
    }

    private void putName(int blockIndex, int offset, String name) {
        byte[] block = blocks[blockIndex];
        int maxLen = Math.min(8, block.length - offset);
        HexUtil.putAscii(block, offset, maxLen, name);
    }
}
