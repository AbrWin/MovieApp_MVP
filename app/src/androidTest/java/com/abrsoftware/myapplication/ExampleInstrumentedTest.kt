package com.abrsoftware.myapplication

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.abrsoftware.myapplication", appContext.packageName)
    }

    @Test
    fun checkConnectivity(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val connectivityManager: ConnectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        assertNull(info)
        assertTrue(info!!.isConnectedOrConnecting())
    }

    @Test
    fun isAppDebugeable(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertTrue(0 != appContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
    }
}