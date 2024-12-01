
#ifndef UNIX_UTILS_H_
#define UNIX_UTILS_H_

#include <jni.h>
#include <stdint.h>

#define ADD_PREFIX(P, S, N, R)                                   \
    if ((N = unix_util_add_package_prefix(P, S)) == NULL) {    \
        goto R;                                           \
    }

#define LOAD_CLASS(E, C, N, R)                   \
     jclass _##C = (*(E))->FindClass((E), N);    \
     if (_##C == NULL) {                         \
         (*(E))->ExceptionClear((E));            \
         goto R;                                 \
     }                                           \
     C = (*(E))->NewGlobalRef((E), _##C);        \
     (*(E))->DeleteLocalRef((E), _##C);          \
     if (C == NULL) {                            \
         goto R;                                 \
     }

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

char* unix_util_concat(const char* one, const char* two);

jint unix_util_register_natives(JNIEnv* env, const char* packagePrefix, const char* className, const JNINativeMethod* methods, jint numMethods);

void unix_utils_throw_by_name(JNIEnv* env, const char* exception, const char* msg);

void unix_utils_throw_by_name_errno(JNIEnv* env, const char* exception, const char* msg, int error);

#endif