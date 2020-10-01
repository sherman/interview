#include <jni.h>
#include <stdint.h>

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

    jlong a = 0;
    jlong b = 1;

    for (jint i = 2; i <= n; i++) {
        jlong next = a + b;
        a = b;
        b = next;
    }
    return b;
}

JNIEXPORT jlong JNICALL
JavaCritical_org_sherman_interview_NativeFunctions_fibonacciFast(jint n) {
    if (n == 0) {
        return 0;
    }

    if (n == 1) {
        return 1;
    }

    jlong a = 0;
    jlong b = 1;

    for (jint i = 2; i <= n; i++) {
        jlong next = a + b;
        a = b;
        b = next;
    }
    return b;
}

JNIEXPORT jlong JNICALL
Java_org_sherman_interview_NativeFunctions_fibonacciFast(JNIEnv* env, jclass cls, jint n) {
    return -1;
}