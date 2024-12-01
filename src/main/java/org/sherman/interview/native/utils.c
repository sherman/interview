
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include "utils.h"

char* unix_util_concat(const char* one, const char* two)
{
    char* result = NULL;
    if (one == NULL) {
        if ((result = (char*) malloc(sizeof(char) * (strlen(two) + 1))) == NULL) {
            return NULL;
        }
        strcpy(result, two);
        return result;
    }
    if ((result = (char*) malloc(sizeof(char) * (strlen(one) + strlen(two) + 1))) == NULL) {
        return NULL;
    }
    strcpy(result, one);
    strcat(result, two);
    return result;
}

jint unix_util_register_natives(JNIEnv* env, const char* packagePrefix, const char* className, const JNINativeMethod* methods, jint numMethods)
{
    int ret = JNI_ERR;
    jclass nativeCls = (*env)->FindClass(env, className);
    if (nativeCls == NULL) {
        return ret;
    }

    ret = (*env)->RegisterNatives(env, nativeCls, methods, numMethods);
    return ret;
}

void unix_utils_throw_by_name(JNIEnv* env, const char* exception, const char* msg) {
    jclass cls = (*env)->FindClass(env, exception);
    if (cls != NULL) {
        (*env)->ThrowNew(env, cls, msg);
    }
}

void unix_utils_throw_by_name_errno(JNIEnv* env, const char* exception, const char* msg, int error)
{
    if (error < 0) {
        error = -error;
    }

    jclass cls = (*env)->FindClass(env, exception);
    if (cls != NULL) {
        char* result = unix_util_concat(msg, unix_util_concat(msg, strerror(error)));
        (*env)->ThrowNew(env, cls, result);
    }
}