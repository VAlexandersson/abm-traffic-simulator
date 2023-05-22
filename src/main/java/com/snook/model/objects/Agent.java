package com.snook.model.objects;

import com.snook.model.objects.behaviour.Rule;

public interface Agent {

    int notifyInterval = 500;
    public void update();
    public void setRule(Rule rule);
}
