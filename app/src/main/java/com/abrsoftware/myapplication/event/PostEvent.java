package com.abrsoftware.myapplication.event;

import com.abrsoftware.myapplication.helper.EventbusHelper;

public class PostEvent {
    private final GeneralEvent generalEvent;
    private final EventbusHelper eventBus;

    public PostEvent() {
        this.eventBus = EventbusHelper.getInstance();
        this.generalEvent = new GeneralEvent();
    }

    public void postEvent(int type) {
        generalEvent.setTypeEvent(type);
        eventBus.post(generalEvent);
    }

    public <T> void postEvent(int type, T statusResponce) {
        generalEvent.setTypeEvent(type);
        if(statusResponce != null){
            generalEvent.setResponce(statusResponce);
            /*
            if(statusResponce instanceof RecommendationResponce){
                generalEvent.setRecommendationRes(statusResponce);
            }*/
        }
        eventBus.post(generalEvent);
    }


}
