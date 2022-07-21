package com.abrsoftware.myapplication.helper

import org.greenrobot.eventbus.EventBus

class EventbusHelper private constructor() : Eventbus {
    private val eventBus: EventBus


    internal object SingletonHolder {
        val INSTANCE = EventbusHelper()
    }

    override fun register(subscribe: Any?) {
        eventBus.register(subscribe)
    }

    override fun unregister(subscribe: Any?) {
        eventBus.unregister(subscribe)
    }

    override fun post(event: Any?) {
        eventBus.post(event)
    }

    companion object;
    init {
        eventBus = EventBus.getDefault()
    }
}