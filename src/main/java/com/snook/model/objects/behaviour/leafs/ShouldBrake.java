package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.SimulationObject;
import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.core.ObjectState;

public class ShouldBrake extends Rule {
    @Override
    public void reset() { start(); }

    @Override
    public void act(SimulationAgent agent, Board board) {
        System.out.println("\n-----ShouldBrake act-----\n-------------------------");
        double agentReactionTime = agent.getReactionTime();
        if(agent.getState() == ObjectState.IDLE) {
            agentReactionTime -= 0.4;

        }

        SimulationObject closestObject = Helper.getRowClosestObjectV2(agent);//(agent.getX(), agent.getY()+1, board, agent.getDirection());
        int closestObjectDistance = Helper.calculateRelativeDistance(agent, closestObject);//calculateRowDifference(agent.getY(), closestObject.getY(), board.getPixelHeight(), agent.getDirection());

        int relativeVelocity = Helper.getRelativeSpeed(agent.getVelocity(), closestObject.getVelocity());
        double reactionDistance = ( relativeVelocity * agentReactionTime );
        double brakingDistance = Helper.getBrakingDistance(relativeVelocity, agent.getMaxAcceleration());

        double stoppingDistance = (reactionDistance + brakingDistance);

        double relativeDistance = closestObjectDistance - stoppingDistance;

        System.out.println("relativeDistance: " + relativeDistance + "\tDistance: " + closestObjectDistance + "\tReactionDistance: " + reactionDistance + "\tBrakingDistance: " + brakingDistance + "\tStoppingDistance: " + stoppingDistance);

        if ( relativeDistance <= 0 ) {
            System.out.println("HEAVY_BRAKE");
            agent.setAcceleration(-(agent.getMaxAcceleration()*2));
            agent.setState(ObjectState.HEAVY_BRAKE);
        } else if ( relativeDistance <= (int) (relativeVelocity*2) ) {
            System.out.println("BRAKE");

            if ((agent.getAcceleration() > 0)) {
                agent.setAcceleration(-2);
            } else {
                agent.setAcceleration(agent.getAcceleration() - 1);
            }

            //agent.setAcceleration(agent.getAcceleration()-1);
            agent.setState(ObjectState.BRAKE);
        } else if ( relativeDistance <= relativeVelocity * 4 ) {
            System.out.println(agent.getState());
            System.out.println("LIGHT_BRAKE");

            agent.setAcceleration(- 1);
            agent.setState(ObjectState.LIGHT_BRAKE);
        } else {
            agent.setState(ObjectState.BUSY);
        }
        succeed();
        System.out.println(" -\t  "+this.state+"\tSHOULDBRAKE");
        return;
    }
}
