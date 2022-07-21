package com.abrsoftware.myapplication.view.home

import com.abrsoftware.myapplication.event.GeneralEvent

class HomeMvp {
    interface View {
        fun showLoading(show: Boolean)
        fun showMsj(msj: String?)
        fun <T> successResponce(responce: T)
    }

    interface Presenter {
        fun oncreate()
        fun onDestroy()
        fun onEventMainThread(event: GeneralEvent?)
        fun getPolular(page: String?)
        fun getLastest(page: String?)
        fun getUpcomming(page: String?)
    }

    interface Repository {
        fun getPolular(page: String?)
        fun getLastest(page: String?)
        fun getUpcomming(page: String?)
    }
}