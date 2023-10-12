package com.snook;

import com.snook.model.SimulationEngine;
import com.snook.model.objects.*;
import com.snook.model.objects.core.ObjectState;
import com.snook.view.ViewPanel;
import java.util.concurrent.*;

public class SimulationPresenter {
    SimulationEngine simulationEngine;
    ViewPanel view;

    CyclicBarrier barrier;

    public SimulationPresenter(SimulationEngine simulationEngine, ViewPanel view) {
        this.simulationEngine = simulationEngine;
        this.view = view;
    }

    public void addObstacle(int x, int y) {
        simulationEngine.addObject(ObstacleFactory.createObstacle(x, y));
    }

    public void addAgent(int x, int y) {
        simulationEngine.addObject(AgentFactory.createAgent(
                        new Observer() {
                            public void agentChanged(SimulationObject object) throws InterruptedException, BrokenBarrierException {
                                if(object.getState() == ObjectState.DEAD) {
                                    simulationEngine.removeObject(object);
                                }
                                barrier.await();
                            }
                        }, x, y)
        );
    }


    public void startSimulation() {
        ScheduledExecutorService viewUpdateExecutor = Executors.newSingleThreadScheduledExecutor();

        barrier = new CyclicBarrier(simulationEngine.getNumberOfAgents()+1);
        view.updateView(simulationEngine.getObjects(), simulationEngine.getBoard());

        viewUpdateExecutor.scheduleAtFixedRate(() -> {
            try {
                simulationEngine.stepAgents();
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) { throw new RuntimeException(e);
            } finally {
                view.updateView(simulationEngine.getObjects(), simulationEngine.getBoard());

                barrier.reset();
            }
        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}
