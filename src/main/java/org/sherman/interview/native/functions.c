#include <jni.h>

JNIEXPORT jint JNICALL
Java_org_sherman_interview_NativeFunctions_add(JNIEnv* env, jclass cls, jint a, jint b) {
    return a + b;
}

JNIEXPORT jlong JNICALL
Java_org_sherman_interview_NativeFunctions_fibonacci(JNIEnv* env, jclass cls, jint n) {
    if (n == 0) {
        return 0;
    }

    if (n == 1) {
        return 1;
    }

    long a = 0;
    long b = 1;

    for (int i = 2; i <= n; i++) {
        long next = a + b;
        a = b;
        b = next;
    }
    return b;
}