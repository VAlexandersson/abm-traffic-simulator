package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.behaviour.Rule;

public class IncreaseAcceleration extends Rule {

    @Override
    public void reset() { start(); }

    @Override
    public void act(SimulationAgent agent, Board board) {
        if(agent.getVelocity() == agent.getMaxVelocity()) {
            System.out.println("-------- INCREASE ACCELERATION - FAILED --------\n---------MAX VELOCITY---------");
            fail();
            return;
        }
        if (agent.getAcceleration() == agent.getMaxAcceleration()) {
            System.out.println("-------- INCREASE ACCELERATION - FAILED --------\n---------MAX ACCELERATION---------");
            fail();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Increasing acceleration: " + agent.getAcceleration() + " -> ");
        if(agent.getAcceleration() < 0) { agent.setAcceleration(0); }
        agent.setAcceleration(agent.getAcceleration()+1);
        sb.append(agent.getAcceleration());
        System.out.println(sb.toString());
        succeed();
    }
}
