package com.abrsoftware.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction;
import com.abrsoftware.myapplication.service.ApiServiceSingleton
import com.abrsoftware.myapplication.view.home.ViewHome
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(ViewHome::class.java,null)
        ApiServiceSingleton.getInstance().apiService.initApiService()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag("com.abrsoftware.myapplication.view.home.ViewHome")
        if(supportFragmentManager.fragments.contains(fragment)){
            finish()
        }else{
            super.onBackPressed()
        }
    }

    fun changeFragment(fragmentClass: Class<out Fragment>, bundle: Bundle?) {
        try {
            val fragment = fragmentClass.newInstance()
            if (bundle != null) {
                fragment.arguments = bundle
            }
            val manager = this.supportFragmentManager
            val tx = manager.beginTransaction()
            tx.replace(R.id.layout_container_new, fragment, fragmentClass.canonicalName)
            tx.addToBackStack(null)
            tx.commit()
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }
}