LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

APP_PLATFORM := android-14

LOCAL_MODULE    := Stella
LOCAL_SRC_FILES := Stella.c

LOCAL_LDLIBS := -llog -landroid -lz

include $(BUILD_SHARED_LIBRARY)