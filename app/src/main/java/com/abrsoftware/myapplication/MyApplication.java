package com.abrsoftware.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

public class MyApplication extends Application {
    private static Context context;
    private static boolean isDebuggable;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    public static Context getContext() {
        return context;
    }

    private static void setContext(Context context) {
        MyApplication.context = context;
    }

    public static boolean isDebuggable() {
        return isDebuggable;
    }

}
