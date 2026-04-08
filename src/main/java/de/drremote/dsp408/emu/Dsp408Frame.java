package de.drremote.dsp408.emu;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

final class Dsp408Frame {
    static final int DLE = 0x10;
    static final int STX = 0x02;
    static final int ETX = 0x03;

    private final byte[] payload;

    Dsp408Frame(byte[] payload) {
        this.payload = payload;
    }

    byte[] payload() {
        return payload;
    }

    int command() {
        return payload.length >= 4 ? payload[3] & 0xFF : -1;
    }

    byte[] encode() {
        byte[] frame = new byte[payload.length + 5];
        int pos = 0;
        frame[pos++] = (byte) DLE;
        frame[pos++] = (byte) STX;
        System.arraycopy(payload, 0, frame, pos, payload.length);
        pos += payload.length;
        frame[pos++] = (byte) DLE;
        frame[pos++] = (byte) ETX;
        frame[pos] = xor(frame, 0, pos);
        return frame;
    }

    static Dsp408Frame read(InputStream in) throws IOException {
        int b;
        while (true) {
            b = in.read();
            if (b < 0) {
                throw new EOFException("Socket closed while waiting for DLE");
            }
            if (b == DLE) {
                int next = in.read();
                if (next < 0) {
                    throw new EOFException("Socket closed after DLE");
                }
                if (next == STX) {
                    break;
                }
            }
        }

        java.io.ByteArrayOutputStream payload = new java.io.ByteArrayOutputStream();
        int prev = -1;
        while (true) {
            int cur = in.read();
            if (cur < 0) {
                throw new EOFException("Socket closed while reading frame payload");
            }
            if (prev == DLE && cur == ETX) {
                int checksum = in.read();
                if (checksum < 0) {
                    throw new EOFException("Socket closed before checksum");
                }
                break;
            }
            if (prev >= 0) {
                payload.write(prev);
            }
            prev = cur;
        }

        return new Dsp408Frame(payload.toByteArray());
    }

    private static byte xor(byte[] bytes, int offset, int endInclusive) {
        byte x = 0;
        for (int i = offset; i <= endInclusive; i++) {
            x ^= bytes[i];
        }
        return x;
    }
}
