   LOCAL_PATH := $(call my-dir)

   include $(CLEAR_VARS)

   LOCAL_MODULE    := FibLib
   LOCAL_SRC_FILES := com_marakana_android_fibonaccinative_FibLib.cpp
   LOCAL_LDLIBS += -llog
   LOCAL_CFLAGS := -g
   include $(BUILD_SHARED_LIBRARY)
   
  