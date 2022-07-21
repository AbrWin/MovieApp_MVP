package com.abrsoftware.myapplication.service

class ApiServiceSingleton private constructor() {
    val apiService: ApiService

    companion object {
        val instance = ApiServiceSingleton()
    }

    init {
        apiService = ApiService()
    }
}