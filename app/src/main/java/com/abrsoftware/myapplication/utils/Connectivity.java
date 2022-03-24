package com.abrsoftware.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

public class Connectivity {
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
