package com.abrsoftware.myapplication.service;

public class ApiServiceSingleton {
    public static final ApiServiceSingleton apiServiceHolder = new ApiServiceSingleton();
    public final ApiService apiService;

    private ApiServiceSingleton() {
        this.apiService = new ApiService();
    }

    public static ApiServiceSingleton getInstance(){
        return apiServiceHolder;
    }
}
