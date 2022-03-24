package com.abrsoftware.myapplication.helper;

import org.greenrobot.eventbus.EventBus;

public class EventbusHelper implements Eventbus {
    private final EventBus eventBus;


    static class SingletonHolder {
        private static final EventbusHelper INSTANCE = new EventbusHelper();
    }

    public static EventbusHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private EventbusHelper() {
        this.eventBus = EventBus.getDefault();
    }

    @Override
    public void register(Object subscribe) {
        this.eventBus.register(subscribe);
    }

    @Override
    public void unregister(Object subscribe) {
        this.eventBus.unregister(subscribe);
    }

    @Override
    public void post(Object event) {
        this.eventBus.post(event);
    }
}
