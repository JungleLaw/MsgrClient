package cn.law.im.client.jni;

public class JNI {
    static {
        System.loadLibrary("native-lib");
    }

    public static native int getServerPort();

    public static native String getServerUrl();
}
