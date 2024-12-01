#include "utils.h"
#include <jni.h>
#include <stdint.h>
#include <errno.h>
#include <unistd.h>

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

JNIEXPORT jint JNICALL
Java_org_sherman_interview_NativeFunctions_posixRead(JNIEnv* env, jclass cls, jint fd, jbyteArray buffer) {
    jsize size = (*env)->GetArrayLength(env, buffer);
    void *buf_ptr = (*env)->GetPrimitiveArrayCritical(env, buffer, NULL);
    ssize_t bytes_read;
    RESTARTABLE(read(fd, buf_ptr, size), bytes_read);
    (*env)->ReleasePrimitiveArrayCritical(env, buffer, buf_ptr, 0);
    if (bytes_read < 0) {
        unix_utils_throw_by_name_errno(env, "java/io/IOException", "Failed to read fd: ", errno);
        return -1;
    }
    return (jint) bytes_read;
}