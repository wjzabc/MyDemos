LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional 

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res

ifeq (PD1303A,$(BBK_PRODUCT_MODEL))
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res_density_1440 $(LOCAL_PATH)/res

else
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
endif

# TODO: Remove dependency of application on the test runner (android.test.runner)
# library.
#LOCAL_JAVA_LIBRARIES := android.test.runner

LOCAL_STATIC_JAVA_LIBRARIES += android-common
LOCAL_JAVA_LIBRARIES += vivo-framework

LOCAL_STATIC_JAVA_LIBRARIES += supportV4 

LOCAL_PACKAGE_NAME := MyDemos 
LOCAL_PROGUARD_ENABLED := disabled #ָ����ǰ��Ӧ�ô򿪻���
#LOCAL_PROGUARD_FLAG_FILES := proguard.cfg #ָ�����������ļ�
LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

###########################################
include $(CLEAR_VARS)

# LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := supportV4:libs/android-support-v4.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := supportV4:libs/android-support-v4.jar 
#LOCAL_PREBUILT_LIBS :=libBspatchApk:libs/armeabi/libBspatchApk.so
LOCAL_MODULE_TAGS := optional
include $(BUILD_MULTI_PREBUILT)

# Use the following include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))