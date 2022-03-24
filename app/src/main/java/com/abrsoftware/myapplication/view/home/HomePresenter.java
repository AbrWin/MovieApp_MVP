package com.abrsoftware.myapplication.view.home;

import com.abrsoftware.myapplication.event.GeneralEvent;
import com.abrsoftware.myapplication.helper.Eventbus;
import com.abrsoftware.myapplication.helper.EventbusHelper;

import org.greenrobot.eventbus.Subscribe;

public class HomePresenter implements HomeMvp.Presenter{

    private HomeMvp.Repository repository;
    private HomeMvp.View view;
    private Eventbus eventbus;

    public HomePresenter(HomeMvp.View view) {
        this.view = view;
        this.eventbus = EventbusHelper.getInstance();
        this.repository = new HomeRepository();
    }

    @Override
    public void oncreate() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
    }

    @Subscribe
    @Override
    public void onEventMainThread(GeneralEvent event) {
        if (event == null) {
            view.showLoading(false);
            return;
        }

        switch (event.getTypeEvent()) {
            case GeneralEvent.SHOW_LOADING:
                view.showLoading(true);
                break;
            case GeneralEvent.SUCCESS_RESPONCE:
                view.showLoading(false);
                view.successResponce(event.getResponce());
                break;
            case GeneralEvent.ERROR_RESPONCE:
                view.showLoading(false);
                view.showMsj("");
                break;
            case GeneralEvent.ERROR:
                view.showLoading(false);
                view.showMsj("");
                break;
        }
    }

    @Override
    public void getPolular(String path) {
        repository.getPolular(path);
    }

    @Override
    public void getLastest(String path) {
        repository.getLastest(path);
    }

    @Override
    public void getUpcomming(String path) {
        repository.getUpcomming(path);
    }
}
