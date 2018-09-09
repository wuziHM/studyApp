package com.example.test.myapplicationtest.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.telephony.TelephonyManager;
import com.example.test.myapplicationtest.utils.ITelephony1;

import android.util.Log;
/**
 * 本类通过反射机制，获取手机的ITelephony对象。
 * 主要用于处理一些系统级别的事件，比如开启手机网络。
 * */
public class PhoneUtils {
    /**
     * 从TelephonyManager中实例化ITelephony，并返回
     */
    static public ITelephony1 getITelephony(TelephonyManager telMgr) throws Exception {
        Method getITelephonyMethod = telMgr.getClass().getDeclaredMethod("getITelephony");
        getITelephonyMethod.setAccessible(true);// 私有化函数也能使用
        return (ITelephony1) getITelephonyMethod.invoke(telMgr);
    }

    @SuppressWarnings("unchecked")
    static public void printAllInform(Class clsShow) {
        try {
            // 取得所有方法
            Method[] hideMethod = clsShow.getDeclaredMethods();
            int i = 0;
            for (; i < hideMethod.length; i++) {
                Log.e("method name", hideMethod[i].getName());
            }
            // 取得所有常量
            Field[] allFields = clsShow.getFields();
            for (i = 0; i < allFields.length; i++) {
                Log.e("Field name", allFields[i].getName());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    /**
     * 判断网络的连接状态
     * @param context
     * @return
     */
    public static boolean isNetworkConnected (Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }

        return false;

    }

    /**
     * 判断gps是否可用
     * @param context
     * @return
     */
    public static boolean isGpsEnable (Context context) {
        LocationManager locManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

        boolean isGps = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return isGps;
    }

    /**
     * 判断wifi gps是否可用
     * @param context
     * @return
     */
    public static boolean isWifiGpsEnable(Context context) {
        LocationManager locManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

        boolean isAGps = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return isAGps;
    }

    /**
     * 获取手机号码
     * @param context
     * @return
     */
//    public static String getMobileNumber(Context context) {
//        String mobile = null;
//        TelephonyManager telManager = (TelephonyManager)
//                context.getSystemService(Context.TELEPHONY_SERVICE);
//        mobile = telManager.getLine1Number ();
//
//        return mobile;
//    }

}
