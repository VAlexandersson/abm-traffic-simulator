package com.snook;

import com.snook.model.Simulation;
import com.snook.model.board.Board;
import com.snook.model.board.TileState;
import com.snook.model.objects.*;
import com.snook.model.objects.core.ObjectState;
import com.snook.view.ViewPanel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulator {

    Simulation simulation;
    ViewPanel view;

    //private Board simulatorBoard;

    CyclicBarrier barrier;

    public Simulator(Simulation simulation, ViewPanel view) {
        this.simulation = simulation;
        //this.simulatorBoard = simulation.getBoard().copy();
        this.view = view;
    }

    public void addObstacle(int x, int y) {
        simulation.addObject(ObstacleFactory.createObstacle(x, y));
        //simulatorBoard.setTileState(x, y, TileState.OBJECT);
    }

    public void addAgent(int x, int y) {
        //simulatorBoard.setTileState(x, y, TileState.AGENT);
        simulation.addObject(AgentFactory.createAgent(
                        new Observer() {
                            public void agentChanged(SimulationObject object) throws InterruptedException, BrokenBarrierException {
                                //simulatorBoard.setTileState(object.getPreviousX(), object.getPreviousY(), TileState.ROAD);
                                //simulatorBoard.setTileState(object.getX(), object.getY(), object.getType()==SimulationObject.Type.AGENT ? TileState.AGENT : TileState.OBJECT);
                                //System.out.println(object.getName() + " -- changed");
                                if(object.getState() == ObjectState.DEAD) {
                                    simulation.removeObject(object);
                                }
                                barrier.await();
                            }
                        }, x, y)
        );
    }


    public void startSimulation() {
        ExecutorService executor = Executors.newFixedThreadPool(simulation.getAgents().size());
        ScheduledExecutorService viewUpdateExecutor = Executors.newSingleThreadScheduledExecutor();

        barrier = new CyclicBarrier(simulation.getAgents().size()+1);
        view.updateObjects(simulation.getObjects());

        //AtomicInteger cd = new AtomicInteger(25);

        viewUpdateExecutor.scheduleAtFixedRate(() -> {
            try {
                for (Agent agent : simulation.getAgents()) {
                    executor.submit((Runnable) agent);
                }
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) { throw new RuntimeException(e);
            } finally {
                view.updateObjects(simulation.getObjects());
                barrier.reset();
                //if(cd.getAndDecrement() == 0) {
                    //for (SimulationObject object : simulation.getObjects()) {
                    //    if(object.getType() == SimulationObject.Type.OBSTACLE) {
                    //        simulation.removeObject(object);
                    //        break;
                    //    }
                    //}
                //}
            }
        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}
