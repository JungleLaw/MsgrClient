/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <string>
/* Header for class cn_law_messager_jni_JNI */

#ifndef _Included_cn_law_messager_jni_JNI
#define _Included_cn_law_messager_jni_JNI
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     cn_law_im_client_jni_JNI
 * Method:    getServerPort
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cn_law_im_client_jni_JNI_getServerPort
        (JNIEnv *, jclass) {
    jint port = 8080;
    return port;
}

/*
 * Class:     cn_law_im_client_jni_JNI
 * Method:    getServerUrl
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_cn_law_im_client_jni_JNI_getServerUrl
        (JNIEnv *env, jclass) {
    std::string url = "192.168.1.106";
    return env->NewStringUTF(url.c_str());
}


#ifdef __cplusplus
}
#endif
#endif