package com.abrsoftware.myapplication.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET(Routes.LASTEST)
    Call<String> getLastest(@Path("page") String nMovie, @Query("api_key") String api_key);

    @GET(Routes.POPULAR)
    Call<String> getPopular(@Path("page") String nMovie, @Query("api_key") String api_key);

    @GET(Routes.UPCOMING)
    Call<String> getUpcoming(@Path("page") String nMovie, @Query("api_key") String api_key);

}
