#include <jni.h>

JNIEXPORT jint JNICALL
Java_org_sherman_interview_NativeFunctions_add(JNIEnv* env, jclass cls, jint a, jint b) {
    return a + b;
}