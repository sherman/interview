package org.sherman.benchmark.java;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;

public class PlatformDependent {
    public static final Unsafe unsafe = getUnsafe();
    private static final Field fdField = getField(FileDescriptor.class, "fd");
    private static final ByteBuffer prototype = ByteBuffer.allocateDirect(0);
    private static final long addressOffset = fieldOffset(Buffer.class, "address");
    private static final long capacityOffset = fieldOffset(Buffer.class, "capacity");

    public static Unsafe getUnsafe() {
        try {
            return (Unsafe) getField(Unsafe.class, "theUnsafe").get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Field getField(Class<?> cls, String name) {
        Field f = findField(cls, name);
        if (f != null) {
            f.setAccessible(true);
        }
        return f;
    }

    public static Field findField(Class<?> cls, String name) {
        try {
            return cls.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static Field getField(String cls, String name) {
        Field f = findField(cls, name);
        if (f != null) {
            f.setAccessible(true);
        }
        return f;
    }

    public static Field findField(String cls, String name) {
        try {
            return findField(Class.forName(cls), name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static long fieldOffset(Class<?> cls, String name) {
        try {
            return unsafe.objectFieldOffset(cls.getDeclaredField(name));
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    public static int getIntVolatile(long address) {
        return unsafe.getIntVolatile(null, address);
    }

    public static void setMemory(long address, int bytes, byte value) {
        unsafe.setMemory(address, bytes, value);
    }

    public static void putByte(long address, byte value) {
        unsafe.putByte(address, value);
    }

    public static byte putByte(long address) {
        return unsafe.getByte(address);
    }

    public static void putInt(long address, int value) {
        unsafe.putInt(address, value);
    }

    public static void putLong(long address, long value) {
        unsafe.putLong(address, value);
    }

    public static long getLong(long address) {
        return unsafe.getLong(address);
    }

    public static int getInt(long address) {
        return unsafe.getInt(address);
    }

    public static void putIntOrdered(long address, int value) {
        unsafe.putOrderedInt(null, address, value);
    }

    public static void putIntVolatile(long address, int value) {
        unsafe.putIntVolatile(null, address, value);
    }

    public static int getFd(FileDescriptor fileDescriptor) {
        try {
            return (int) fdField.get(fileDescriptor);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static long allocateMemory(long bytes) {
        return unsafe.allocateMemory(bytes);
    }

    public static void freeMemory(long address) {
        unsafe.freeMemory(address);
    }

    public static ByteBuffer wrap(long address, int count) {
        ByteBuffer result = prototype.duplicate();
        unsafe.putLong(result, addressOffset, address);
        unsafe.putInt(result, capacityOffset, count);
        result.limit(count);
        return result;
    }
}
