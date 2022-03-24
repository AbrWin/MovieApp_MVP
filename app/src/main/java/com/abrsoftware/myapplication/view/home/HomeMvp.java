package com.abrsoftware.myapplication.view.home;

import com.abrsoftware.myapplication.event.GeneralEvent;

public class HomeMvp {
    public interface View {
        void showLoading(boolean show);

        void showMsj(String msj);

        <T> void successResponce(T responce);
    }

    public interface Presenter {
        void oncreate();

        void onDestroy();

        void onEventMainThread(GeneralEvent event);

        void getRecommendation(String page, String numMoview);

        void getPolular(String userId);

        void getLastest(String userId);

        void getUpcomming(String userId);
    }

    public interface Repository {
        void getRecommendation(String page, String numMoview);

        void getPolular(String userId);

        void getLastest(String userId);

        void getUpcomming(String userId);
    }
}
