package com.abrsoftware.myapplication.service;

import com.abrsoftware.myapplication.MyApplication;
import com.abrsoftware.myapplication.R;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {
    private ApiServiceInterface apiClient;

    public void initApiService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(MyApplication.getContext().getString(R.string.api_end_point))
                .addConverterFactory(ScalarsConverterFactory.create());
        setApiClient(builder.build().create(ApiServiceInterface.class));
    }

    public void setApiClient(ApiServiceInterface apiClient) {
        this.apiClient = apiClient;
    }

    public ApiServiceInterface getApiClient() {
        return apiClient;
    }
}
