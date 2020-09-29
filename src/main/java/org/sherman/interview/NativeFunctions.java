package org.sherman.interview;

public class NativeFunctions {
    static {
        if (!NativeLibrary.IS_SUPPORTED) {
            throw new IllegalArgumentException("Native lib is not supported!");
        }
    }
    public static native int add(int a, int b);
}
