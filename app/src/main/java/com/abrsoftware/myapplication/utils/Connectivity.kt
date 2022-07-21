package com.abrsoftware.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.util.*

object Connectivity {
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = Objects.requireNonNull(connectivityManager).activeNetworkInfo
        return info != null && info.isConnectedOrConnecting
    }
}