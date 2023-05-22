package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.SimulationObject;
import com.snook.model.objects.behaviour.Rule;

public class LaneIsFree extends Rule {

    public LaneIsFree() {
        super();
    }

    @Override
    public void reset() {
        start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {
        SimulationObject closestObject = Helper.getRowClosestObjectV2(agent);//(agent.getX(), agent.getY()+1, board, agent.getDirection());
        if( closestObject == null || closestObject.getVelocity() > agent.getVelocity()) {
            fail();
            System.out.println(" - " + this.state.toString().toUpperCase() + "\tOBJECTINFRONT\t FASTER");
            return;
        }
    }
}
