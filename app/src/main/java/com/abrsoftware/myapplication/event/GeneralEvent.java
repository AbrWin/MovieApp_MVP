package com.abrsoftware.myapplication.event;

public class GeneralEvent {
    public static final int ERROR = 0;
    public static final int SUCCESS_RESPONCE = 1;
    public static final int SHOW_LOADING = 2;
    public static final int ERROR_RESPONCE = 4;

    private int typeEvent;
    private String message;
    private Object responce;

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Object getResponce() {
        return responce;
    }

    public void setResponce(Object responce) {
        this.responce = responce;
    }
}
