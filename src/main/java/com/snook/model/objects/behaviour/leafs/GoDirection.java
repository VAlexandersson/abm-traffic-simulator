package com.snook.model.objects.behaviour.leafs;

import com.snook.model.objects.behaviour.Rule;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.core.Direction;

public class GoDirection extends Rule {
    Direction direction;
    public GoDirection(Direction direction) {
        super();
        this.direction = direction;
    }

    public void reset() { start(); }

    @Override
    public void act(SimulationAgent agent, Board board) {
        if (isRunning()) {
            System.out.println(" - \tGoDirection act");

            agent.updatePosition();
            //agent.setObstaclePosition();
            agent.setDirection(direction);
            succeed();
            return;
        }
        fail();
    }
//    private void moveAgent(VehicleAgent agent) {
//        agent.updateMovement();
//        switch (direction) {
//            case NORTH -> vehicleAgent.setYModulo(vehicleAgent.getY()-vehicleAgent.getVelocity());
//            case SOUTH -> vehicleAgent.setYModulo(vehicleAgent.getY()+vehicleAgent.getVelocity());
//            case WEST -> vehicleAgent.setXModulo(vehicleAgent.getX()-vehicleAgent.getVelocity());
//            case EAST -> vehicleAgent.setXModulo(vehicleAgent.getX()+vehicleAgent.getVelocity());
//        }
//    }
}
