package com.snook.model;

import com.snook.model.board.Board;
import com.snook.model.objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation  {
    private final ArrayList<SimulationObject> objects;
    private final Board board;


    public Simulation(ArrayList<SimulationObject> objects, Board board) {
        this.objects = objects;
        this.board = board;
    }

    public void addObject(SimulationObject object) {
        board.addSimulationObject(object);
        objects.add(object);
    }
    public List<SimulationAgent> getAgents() {
        ArrayList<SimulationAgent> agents = new ArrayList<>();
        for (SimulationObject object : objects) {
            if(object instanceof SimulationAgent) {
                agents.add((SimulationAgent) object);
            }
        }
        return agents;
    }
    public ArrayList<SimulationObject> getObjects() { return objects; }
    public void removeObject(SimulationObject object) {
        board.getSimulationObjects().remove(object);
        objects.remove(object);
    }
    public Board getBoard() { return board; }


}
