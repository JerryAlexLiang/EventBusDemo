package com.example.demo02_netreceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * 创建日期：2018/2/22 on 下午8:07
 * 描述: 网络相关辅助类
 * 作者: liangyang
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
//        Intent intent = new Intent("/");
//        ComponentName cm = new ComponentName("com.android.settings",
//                "com.android.settings.WirelessSettings");
//        intent.setComponent(cm);
//        intent.setAction("android.intent.action.VIEW");
//        activity.startActivityForResult(intent, 0);

        //不同的Android版本有不同的调用方法，进行区分当前手机的版本号
        try {
            if (10 < Build.VERSION.SDK_INT) {
                activity.startActivity(new Intent(
                        "android.settings.SETTINGS"));
                return;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            return;
        }
        activity.startActivity(new Intent(
                "android.settings.WIRELESS_SETTINGS"));
    }

}
