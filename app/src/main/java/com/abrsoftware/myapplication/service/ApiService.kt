package com.abrsoftware.myapplication.service

import retrofit2.Retrofit
import com.abrsoftware.myapplication.MyApplication
import com.abrsoftware.myapplication.R
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiService {
    var apiClient: ApiServiceInterface? = null

    fun initApiService() {
        val builder = Retrofit.Builder()
            .baseUrl(MyApplication.getContext().getString(R.string.api_end_point))
            .addConverterFactory(ScalarsConverterFactory.create())
        apiClient = builder.build().create(ApiServiceInterface::class.java)
    }
}