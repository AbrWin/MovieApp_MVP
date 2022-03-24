package com.abrsoftware.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abrsoftware.myapplication.service.ApiServiceSingleton
import com.abrsoftware.myapplication.view.home.ViewHome

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(ViewHome::class.java,null)
        ApiServiceSingleton.getInstance().apiService.initApiService()
    }

    fun changeFragment(fragmentClass: Class<out Fragment>, bundle: Bundle?) {
        try {
            val fragment = fragmentClass.newInstance()
            if (bundle != null) {
                fragment.arguments = bundle
            }
            val manager = this.supportFragmentManager
            val tx = manager.beginTransaction()
            tx.replace(R.id.layout_container, fragment, fragmentClass.canonicalName)
            tx.addToBackStack(null)
            tx.commit()
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }
}