package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.SimulationObject;
import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.core.Direction;

import static com.snook.model.objects.behaviour.leafs.Helper.*;
import static com.snook.model.objects.core.Direction.NORTH;
import static com.snook.model.objects.core.Direction.SOUTH;

public class OtherLaneFree extends Rule {
    @Override
    public void reset() {

    }

    @Override
    public void act(SimulationAgent agent, Board board) {
        String lane = (drivingRightLane(agent))? "right" : "left";

        int x = (drivingRightLane(agent))? agent.getX()-1 : agent.getX()+1;
        SimulationObject closestObject = getRowClosestObject(x, agent.getY()+agent.getLength()*2, board, agent.getDirection());


        SimulationObject frontClosestObject = getRowClosestObject(x, agent.getY(), board, agent.getDirection());
        SimulationObject rearClosestObject = getRowClosestObject(x, agent.getY(), board, SOUTH);

        if(  (frontClosestObject == null && rearClosestObject == null)) {
            succeed();
            System.out.println(" - " + this.state.toString().toUpperCase() + "\tOTHERLANE FREEE\t");
            return;
        }
        int rearDistance = 9000, frontDistance = 9000;
        if(rearClosestObject != null) rearDistance = Math.abs(calculateRowDifference(agent.getY(), rearClosestObject.getY(), board.getPixelHeight(), SOUTH));
        if(frontClosestObject != null) frontDistance = calculateRowDifference(agent.getY(), frontClosestObject.getY(), board.getPixelHeight(), agent.getDirection());
        System.out.println("\n\nRear distance: " + rearDistance + "\tFront distance: " + frontDistance);
        System.out.println("Agent length: " + agent.getLength() + "* 2 " + (agent.getLength()*2)+"\n\n");

        int distance =calculateRowDifference(agent.getY(), closestObject.getY(), board.getPixelHeight(), NORTH);// calculateRelativeDistance()//getDistanceFromClosestObject(agent, lane, NORTH);
        System.out.println("Distance from closest object in " + lane + " lane: " + distance);
        if(rearDistance > agent.getLength()*2 && frontDistance > 300) {
            succeed();
            System.out.println(" - " + this.state.toString().toUpperCase() + "\tOTHERLANE FREEE\t");
            return;

//        if(distance < 200 && closestObject.getY() > agent.getY()) {
        //    fail();
          //  System.out.println(" - " + this.state.toString().toUpperCase() + "\tOTHERLANE FREEE\t");
           // return;
        }
       fail();
        return;
    }
}
