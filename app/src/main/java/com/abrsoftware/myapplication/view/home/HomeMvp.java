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

        void getPolular(String page);

        void getLastest(String page);

        void getUpcomming(String page);
    }

    public interface Repository {

        void getPolular(String page);

        void getLastest(String page);

        void getUpcomming(String page);
    }
}
