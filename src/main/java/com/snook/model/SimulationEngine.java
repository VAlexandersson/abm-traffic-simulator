package com.snook.model;

import com.snook.model.board.Board;
import com.snook.model.objects.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    private final ExecutorService executor;
    private final ArrayList<SimulationObject> objects;
    private final ArrayList<Runnable> agents;
    private final Board board;


    public SimulationEngine(ArrayList<SimulationObject> objects, Board board) {
        this.objects = objects;
        this.board = board;
        agents = new ArrayList<>();
        executor = Executors.newCachedThreadPool();
    }

    public void addObject(SimulationObject object) {
        board.addSimulationObject(object);
        objects.add(object);
        if(object instanceof SimulationAgent) {
            agents.add((Runnable) object);
        }
    }

    public ArrayList<SimulationObject> getObjects() { return objects; }
    public void removeObject(SimulationObject object) {
        board.getSimulationObjects().remove(object);
        objects.remove(object);
    }
    public Board getBoard() { return board; }

    public void stepAgents() {
        for (Runnable agent : agents) {
            executor.submit(agent);
        }
    }

    public int getNumberOfAgents() {
        return agents.size();
    }

}
