package com.abrsoftware.myapplication.service;

import com.abrsoftware.myapplication.MyApplication;
import com.abrsoftware.myapplication.R;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "text/plain";
    private static final String TEXT_PLAIN = "text/plain";
    private static final String LANGUAGE = "idioma";
    private static final String TOKEN = "Authorization";
    private static final String ES = "es";
    private static final String VERSION = "version";
    private static final String OPERATIONID = "operacionId";
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
