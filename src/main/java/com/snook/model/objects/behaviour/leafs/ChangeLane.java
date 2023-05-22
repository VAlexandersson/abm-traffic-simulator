package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.core.Direction;

public class ChangeLane extends Rule {
    @Override
    public void reset() {
        start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {
        System.out.println("Changing lane\n\n\n");

        if(Helper.drivingRightLane(agent)) agent.setDirection(Direction.NORTH_WEST);
        else agent.setDirection(Direction.NORTH_EAST);
        agent.setAcceleration(agent.getMaxAcceleration());
        agent.updatePosition();
        agent.setDirection(Direction.NORTH);
        succeed();
    }
}
