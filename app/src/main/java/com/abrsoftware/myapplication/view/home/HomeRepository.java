package com.abrsoftware.myapplication.view.home;

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
    public void getPolular(String page) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            return;
        }

        apiService.getApiClient().getPopular(page,  MyApplication.getContext().getString(R.string.api_key)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ListMoviesPopular responce = gson.fromJson(response.body(), ListMoviesPopular.class);
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            }
        });
    }

    @Override
    public void getLastest(String page) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            return;
        }

        apiService.getApiClient().getLastest(page,  MyApplication.getContext().getString(R.string.api_key)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SingleMovie responce = gson.fromJson(response.body(), SingleMovie.class);
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            }
        });
    }

    @Override
    public void getUpcomming(String page) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            return;
        }

        apiService.getApiClient().getUpcoming(page,  MyApplication.getContext().getString(R.string.api_key)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ListMoviesUpcomming responce = gson.fromJson(response.body(), ListMoviesUpcomming.class);
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ERROR, MyApplication.getContext().getString(R.string.errorConnectivity));
            }
        });
    }
}
