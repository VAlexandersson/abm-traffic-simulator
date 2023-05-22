package com.snook.model.objects;

import java.util.concurrent.BrokenBarrierException;

public interface Observer {
    public void agentChanged(SimulationObject agent) throws InterruptedException, BrokenBarrierException;
}
