package com.abrsoftware.myapplication.view.home

import com.abrsoftware.myapplication.view.home.HomeMvp.Presenter
import com.abrsoftware.myapplication.helper.Eventbus
import org.greenrobot.eventbus.Subscribe
import com.abrsoftware.myapplication.event.GeneralEvent
import com.abrsoftware.myapplication.helper.EventbusHelper

class HomePresenter(private val view: HomeMvp.View) : Presenter {
    private val repository: HomeMvp.Repository
    private val eventbus: Eventbus

    override fun oncreate() {
        eventbus.register(this)
    }

    override fun onDestroy() {
        eventbus.unregister(this)
    }

    @Subscribe
    override fun onEventMainThread(event: GeneralEvent?) {
        if (event == null) {
            view.showLoading(false)
            return
        }
        when (event.typeEvent) {
            GeneralEvent.SHOW_LOADING -> view.showLoading(true)
            GeneralEvent.SUCCESS_RESPONCE -> {
                view.showLoading(false)
                view.successResponce(event.responce)
            }
            GeneralEvent.ERROR_RESPONCE -> {
                view.showLoading(false)
                view.showMsj("")
            }
            GeneralEvent.ERROR -> {
                view.showLoading(false)
                view.showMsj("")
            }
        }
    }

    override fun getPolular(page: String?) {
        repository.getPolular(page)
    }

    override fun getLastest(page: String?) {
        repository.getLastest(page)
    }

    override fun getUpcomming(page: String?) {
        repository.getUpcomming(page)
    }

    init {
        eventbus = EventbusHelper.SingletonHolder.INSTANCE
        repository = HomeRepository()
    }
}