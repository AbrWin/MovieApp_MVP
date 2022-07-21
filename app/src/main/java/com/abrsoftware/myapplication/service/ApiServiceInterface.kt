package com.abrsoftware.myapplication.service

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET(Routes.LASTEST)
    fun getLastest(
        @Path("page") nMovie: String?,
        @Query("api_key") api_key: String?
    ): Response<String?>?

    @GET(Routes.POPULAR)
    fun getPopular(
        @Path("page") nMovie: String?,
        @Query("api_key") api_key: String?
    ): Response<String?>?

    @GET(Routes.UPCOMING)
    fun getUpcoming(
        @Path("page") nMovie: String?,
        @Query("api_key") api_key: String?
    ): Response<String?>?
}