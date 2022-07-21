package com.abrsoftware.myapplication.event

class GeneralEvent {
    var typeEvent = 0
    var responce: Any? = null

    companion object {
        const val ERROR = 0
        const val SUCCESS_RESPONCE = 1
        const val SHOW_LOADING = 2
        const val ERROR_RESPONCE = 4
    }
}