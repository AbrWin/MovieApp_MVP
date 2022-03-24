package com.abrsoftware.myapplication.helper;

public interface Eventbus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);
}
