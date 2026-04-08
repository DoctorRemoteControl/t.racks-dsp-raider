package de.drremote.dsp408.emu;

final class HexUtil {
    private HexUtil() {}

    static byte[] parseHex(String text) {
        String normalized = text.replace(" ", "").replace("\n", "").replace("\r", "").replace("\t", "");
        if ((normalized.length() & 1) != 0) {
            throw new IllegalArgumentException("Odd hex length: " + normalized.length());
        }
        byte[] out = new byte[normalized.length() / 2];
        for (int i = 0; i < normalized.length(); i += 2) {
            out[i / 2] = (byte) Integer.parseInt(normalized.substring(i, i + 2), 16);
        }
        return out;
    }

    static String toHex(byte[] data) {
        return toHex(data, 0, data.length);
    }

    static String toHex(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder(length * 3);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(String.format("%02X", data[offset + i] & 0xFF));
        }
        return sb.toString();
    }

    static void putAscii(byte[] target, int offset, int maxLen, String text) {
        byte[] bytes = text.getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        int copy = Math.min(maxLen, bytes.length);
        System.arraycopy(bytes, 0, target, offset, copy);
        for (int i = copy; i < maxLen; i++) {
            target[offset + i] = 0x20;
        }
    }
}
