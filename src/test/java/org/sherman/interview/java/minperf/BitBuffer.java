package org.sherman.interview.java.minperf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * A simple bit buffer. It is partially optimized for reading, but writing is
 * relatively slow. Writing will only add bits (bitwise or with existing bits).
 */
public class BitBuffer {

    public final long[] data;
    private int pos;

    public BitBuffer(long bits) {
        this.data = new long[(int) ((bits + 63) / 64)];
    }

    public BitBuffer(byte[] data) {
        this.data = new long[(data.length + 7) / 8];
        if (this.data.length != data.length * 8) {
            data = Arrays.copyOf(data, this.data.length * 8);
        }
        ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).asLongBuffer().get(this.data);
    }

    /**
     * Create a buffer that shared the byte data, but uses a separate position
     * (initially 0).
     *
     * @param buffer the buffer
     */
    public BitBuffer(BitBuffer buffer) {
        this.data = buffer.data;
    }

    public void write(BitBuffer bits) {
        int count = bits.pos;
        bits.pos = 0;
        // for (int i = 0; i < count; i++) {
        //     writeBit(bits.readBit());
        // }
        int i = 0;
        for (; i < count - 31; i += 32) {
            writeNumber(bits.readNumber(32), 32);
        }
        for (; i < count; i++) {
            writeBit(bits.readBit());
        }
    }

    public int position() {
        return pos;
    }

    public void seek(int pos) {
        this.pos = pos;
    }

    /**
     * Read a number.
     *
     * @param bitCount the number of bits, at most 63
     * @return the value
     */
    public long readNumber(int bitCount) {
        long x = readNumber(pos, bitCount);
        pos += bitCount;
        return x;
    }

    public long readLong() {
        return (readNumber(32) << 32) | readNumber(32);
    }

    /**
     * Read a number.
     *
     * @param pos      the position
     * @param bitCount the number of bits, at most 63
     * @return the value
     */
    public long readNumber(long pos, int bitCount) {
        if (bitCount == 0) {
            return 0;
        }
        int remainingBits = 64 - ((int) pos & 63);
        int index = (int) (pos >>> 6);
        long x = data[index];
        if (bitCount <= remainingBits) {
            x >>>= remainingBits - bitCount;
            return x & ((1L << bitCount) - 1);
        }
        x = x & ((1L << remainingBits) - 1);
        return (x << (bitCount - remainingBits)) |
            (data[index + 1] >>> (64 - bitCount + remainingBits));
    }

    /**
     * Fold a signed number into an unsigned number. Negative numbers are odd,
     * and positive numbers are even. For example, -5 is converted to 11, and 5
     * to 10.
     *
     * @param x a signed number
     * @return an unsigned number
     */
    public static long foldSigned(long x) {
        return x > 0 ? x * 2 - 1 : -x * 2;
    }

    /**
     * Unfold an unsigned number into a signed number.
     *
     * @param x an unsigned number
     * @return a signed number
     */
    public static long unfoldSigned(long x) {
        return ((x & 1) == 1) ? (x + 1) / 2 : -(x / 2);
    }

    public void writeBit(long x) {
        if (x == 1) {
            data[pos >>> 6] |= 1L << (63 - (pos & 63));
        }
        pos++;
    }

    public long readBit() {
        return (data[pos >>> 6] >>> (63 - (pos++ & 63))) & 1;
    }

    public int readUntilZero(int pos) {
        int remainingBits = 64 - (pos & 63);
        int index = pos >>> 6;
        long x = data[index] << (64 - remainingBits);
        int count = Long.numberOfLeadingZeros(~x);
        if (count < remainingBits) {
            return count;
        }
        return readUntilZeroMore(count, index);
    }

    private int readUntilZeroMore(int count, int index) {
        while (true) {
            long x = data[++index];
            if (x == -1L) {
                count += 64;
                continue;
            }
            return count + Long.numberOfLeadingZeros(~x);
        }
    }

    private int readUntilZero() {
        int len = readUntilZero(pos);
        pos += len + 1;
        return len;
    }

    public void writeGolombRice(int shift, long value) {
        writeGolombRiceFast(shift, value);
    }

    public void writeGolombRiceSlow(int shift, long value) {
        long q = value >>> shift;
        for (int i = 0; i < q; i++) {
            writeBit(1);
        }
        writeBit(0);
        for (int i = shift - 1; i >= 0; i--) {
            writeBit((value >>> i) & 1);
        }
    }

    public void writeGolombRiceFast(int shift, long value) {
        long q = value >>> shift;
        if (q < 63) {
            long m = (2L << q) - 2;
            writeNumber(m, (int) (q + 1));
        } else {
            for (int i = 0; i < q; i++) {
                writeBit(1);
            }
            writeBit(0);
        }
        writeNumber(value & ((1L << shift) - 1), shift);
        // for (int i = shift - 1; i >= 0; i--) {
        //     writeBit((value >>> i) & 1);
        // }
    }

//    public void writeVarLong(long x) {
//        while ((x & ~0x7f) != 0) {
//            writeNumber((0x80 | (x & 0x7f)) & 0xff, 8);
//            x >>>= 7;
//        }
//        writeNumber(x & 0xff, 8);
//    }

//    public long readVarLong() {
//        long x = buff.get();
//        if (x >= 0) {
//            return x;
//        }
//        x &= 0x7f;
//        for (int s = 7; s < 64; s += 7) {
//            long b = buff.get();
//            x |= (b & 0x7f) << s;
//            if (b >= 0) {
//                break;
//            }
//        }
//        return x;
//    }

    public long readGolombRice(int pos, int shift) {
        int q = readUntilZero(pos);
        return (q << shift) | readNumber(pos + q + 1, shift);
    }

    public long readGolombRice(int shift) {
        long q = readUntilZero();
        return (q << shift) | readNumber(shift);
        // int q = 0;
        // while (readBit() == 1) {
        //     q++;
        // }
        // long x = ((long) q) << shift;
        // for (int i = shift - 1; i >= 0; i--) {
        //     x |= readBit() << i;
        // }
        // return x;
    }

    public void skipGolombRice(int shift) {
        pos = skipGolombRice(pos, shift);
    }

    public int skipGolombRice(int pos, int shift) {
        int q = readUntilZero(pos);
        return pos + q + 1 + shift;
    }

    public void writeEliasDelta(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        int q = 64 - Long.numberOfLeadingZeros(value);
        int qq = 31 - Integer.numberOfLeadingZeros(q);
        for (int i = 0; i < qq; i++) {
            writeBit(0);
        }
        for (int i = qq; i >= 0; i--) {
            writeBit((q >>> i) & 1);
        }
        for (int i = q - 2; i >= 0; i--) {
            writeBit((value >>> i) & 1);
        }
    }

    public long readEliasDelta() {
        int qq = 0;
        while (readBit() == 0) {
            qq++;
        }
        long q = 1;
        for (int i = qq; i > 0; i--) {
            q = (q << 1) | readBit();
        }
        long x = 1;
        for (long i = q - 2; i >= 0; i--) {
            x = (x << 1) | readBit();
        }
        return x;
    }

    /**
     * Write a number of bits. The most significant bit is written first.
     *
     * @param x        the number
     * @param bitCount the number of bits, at most 63
     */
    public void writeNumber(long x, int bitCount) {
        // while (bitCount-- > 0) {
        //     writeBit((x >>> bitCount) & 1);
        // }
        if (bitCount == 0) {
            return;
        }
        int remainingBits = 64 - (pos & 63);
        int index = pos >>> 6;
        if (bitCount <= remainingBits) {
            data[index] |= x << (remainingBits - bitCount);
        } else {
            data[index] |= x >>> (bitCount - remainingBits);
            data[index + 1] |= x << (64 - bitCount + remainingBits);
        }
        pos += bitCount;
    }

    public void clearBits(int bitCount) {
        if (bitCount == 0) {
            return;
        }
        int remainingBits = 64 - (pos & 63);
        int index = pos >>> 6;
        long x = (1 << bitCount) - 1;
        if (bitCount <= remainingBits) {
            data[index] &= ~(x << (remainingBits - bitCount));
        } else {
            data[index] &= ~(x >>> (bitCount - remainingBits));
            data[index + 1] &= ~(x << (64 - bitCount + remainingBits));
        }
        pos += bitCount;
    }

    public byte[] toByteArray() {
        byte[] d = new byte[data.length * 8];
        ByteBuffer.wrap(d).order(ByteOrder.BIG_ENDIAN).asLongBuffer()
            .put(data, 0, (pos + 63) / 64);
        if ((pos + 7) / 8 == d.length) {
            return d;
        }
        return Arrays.copyOf(d, (pos + 7) / 8);
    }

    public void clear() {
        Arrays.fill(data, 0);
    }

    public long[] getLongArray() {
        return data;
    }

    public static int getGolombRiceSize(int shift, long value) {
        return (int) ((value >>> shift) + 1 + shift);
    }

    public static int getEliasDeltaSize(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        int q = 64 - Long.numberOfLeadingZeros(value);
        int qq = 31 - Integer.numberOfLeadingZeros(q);
        int len = qq + qq + q;
        return len;
    }
}
