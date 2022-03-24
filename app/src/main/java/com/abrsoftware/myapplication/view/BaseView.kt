package com.abrsoftware.myapplication.view

import android.app.Activity
import android.content.Context

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseView : Fragment() {
    lateinit var rootView: View
    lateinit var activity: Activity
    lateinit var myContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = activity
        this.myContext = activity.applicationContext
    }
}