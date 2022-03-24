package com.abrsoftware.myapplication.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET(Routes.LASTEST)
    Call<String> getLastest(@Path("page") String nMovie, @Path("apiKey") String api_key);

    @GET(Routes.POPULAR)
    Call<String> getPopular(@Path("page") String nMovie, @Path("movieNum") String movieNum, @Path("apiKey") String api_key);

    @GET(Routes.UPCOMING)
    Call<String> getUpcoming(@Path("nMovie") String nMovie, @Path("nMovie") String api_key);

    @GET(Routes.RECOMMENDATION)
    Call<String> getRecomendation(@Path("page") String nMovie, @Path("movieNum") String movieNum, @Query("api_key") String api_key);
}
