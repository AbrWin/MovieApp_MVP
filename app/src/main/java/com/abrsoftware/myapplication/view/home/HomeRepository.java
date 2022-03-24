package com.abrsoftware.myapplication.view.home;

import android.util.Log;

import com.abrsoftware.myapplication.MyApplication;
import com.abrsoftware.myapplication.R;
import com.abrsoftware.myapplication.event.GeneralEvent;
import com.abrsoftware.myapplication.event.PostEvent;
import com.abrsoftware.myapplication.service.ApiService;
import com.abrsoftware.myapplication.service.ApiServiceSingleton;
import com.abrsoftware.myapplication.utils.Connectivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository extends PostEvent implements HomeMvp.Repository{

    private final Gson gson;
    private final ApiService apiService;

    public HomeRepository() {
        apiService = ApiServiceSingleton.apiServiceHolder.apiService;
        gson = new Gson();
    }


    @Override
    public void getRecommendation(String page, String numMoview) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            return;
        }

        apiService.getApiClient().getRecomendation(page, numMoview, MyApplication.getContext().getString(R.string.api_key)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                RecommendationResponce responce = gson.fromJson(response.body(), RecommendationResponce.class);
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            }
        });


    }

    @Override
    public void getPolular(String userId) {

    }

    @Override
    public void getLastest(String userId) {

    }

    @Override
    public void getUpcomming(String userId) {

    }
}
