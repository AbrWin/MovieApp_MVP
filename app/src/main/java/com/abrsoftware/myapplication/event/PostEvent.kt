package com.abrsoftware.myapplication.event

import com.abrsoftware.myapplication.event.GeneralEvent
import com.abrsoftware.myapplication.helper.EventbusHelper

open class PostEvent {
    private val generalEvent: GeneralEvent
    private val eventBus: EventbusHelper

    fun postEvent(type: Int) {
        generalEvent.typeEvent = type
        eventBus.post(generalEvent)
    }

    fun <T> postEvent(type: Int, statusResponce: T?) {
        generalEvent.typeEvent = type
        if (statusResponce != null) {
            generalEvent.responce = statusResponce
        }
        eventBus.post(generalEvent)
    }

    init {
        eventBus = EventbusHelper.SingletonHolder.INSTANCE
        generalEvent = GeneralEvent()
    }
}