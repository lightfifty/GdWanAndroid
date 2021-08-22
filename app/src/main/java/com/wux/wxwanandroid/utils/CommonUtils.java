package com.wux.wxwanandroid.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class CommonUtils {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
