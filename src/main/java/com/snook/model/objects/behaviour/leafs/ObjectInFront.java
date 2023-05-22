package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.SimulationObject;
import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.core.Direction;
import com.snook.model.objects.core.ObjectState;

public class ObjectInFront extends Rule {

    public ObjectInFront() {
        super();
    }

    @Override
    public void reset() {
        start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {

        SimulationObject closestObject = Helper.getRowClosestObject(agent.getX(), agent.getY(), board, agent.getDirection());

        if( closestObject == null || closestObject == agent || closestObject.getVelocity() > agent.getVelocity()) {
            fail();
            System.out.println(" - " + this.state.toString().toUpperCase() + "\tOBJECTINFRONT\t FASTER");
            return;
        }
        System.out.print("Inside ObjectInFront " + agent.getName()  );

        int closestObjectDistance = Helper.calculateRelativeDistance(agent, closestObject);//Helper.calculateRowDifference(agent.getY(), closestObject.getY(), board.getPixelHeight(), Direction.NORTH);


        if (closestObjectDistance >= 150) {
            fail();
        } else {
            succeed();
        }
        System.out.println(" - " + this.state.toString().toUpperCase() + "\tOBJECTINFRONT ");

    }
}
