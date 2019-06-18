#include <stdbool.h>
#include <jni.h>
#include <time.h>
#include <unistd.h>

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    return JNI_VERSION_1_4;
}

int getTimeOfDay() {
    struct timeval tv;
    struct timezone tz;
    gettimeofday(&tv, &tz);
    return (tv.tv_sec * 1000 + tv.tv_usec / 1000);
}

jboolean Java_com_nekolaboratory_Stella_Core_checkSpeedHack(JNIEnv *env, jobject instance) {
    int start = 0, end = 0, time_data = 0;
    start = getTimeOfDay();
    sleep(1);
    end = getTimeOfDay();
    time_data = end - start;
    if (time_data > 1100 || time_data < 1000) {
        return true;
    }
    return false;
}