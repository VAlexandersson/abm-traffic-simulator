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
        System.out.println("\n\n\n%%%%%%%%%%%%%%%%%%\nOther lane free");
        int x = (drivingRightLane(agent))? agent.getX()-1 : agent.getX()+1;

        SimulationObject frontClosestObject = getRowClosestObject(x, agent.getY(), board, agent.getDirection());
        SimulationObject rearClosestObject = getRowClosestObject(x, agent.getY(), board, SOUTH);

        if(  (frontClosestObject == null && rearClosestObject == null)) {
            succeed();
            System.out.println(" - NULLL NUULLLN  " + this.state.toString().toUpperCase() + "\tOTHERLANE FREEE\t");
            return;
        }
        int rearDistance = 9000, frontDistance = 9000;
        if(rearClosestObject != null) {
            System.out.println("Rear closest object: " + rearClosestObject.getName() + "\t" + rearClosestObject.getX() + "\t" + rearClosestObject.getY());
            rearDistance = (agent.getY() == rearClosestObject.getY()) ? 0 : Math.abs(calculateRowDifference(agent.getY(), rearClosestObject.getY(), board.getPixelHeight(), SOUTH));
            //rearDistance = Math.abs(calculateRowDifference(agent.getY(), rearClosestObject.getY(), board.getPixelHeight(), SOUTH));
        }
        if(frontClosestObject != null) {
            System.out.println("Front closest object: " + frontClosestObject.getName() + "\t" + frontClosestObject.getX() + "\t" + frontClosestObject.getY());
            frontDistance = (agent.getY() == frontClosestObject.getY()) ? 0 : calculateRowDifference(agent.getY(), frontClosestObject.getY(), board.getPixelHeight(), agent.getDirection());

        }
        System.out.println("Agent: " + agent.getName() + "\t" + agent.getX() + "\t" + agent.getY());

        System.out.println("\n\nRear distance: " + rearDistance + "\tFront distance: " + frontDistance);
        System.out.println("Agent length: " + agent.getLength() + "* 2 " + (agent.getLength()*2)+"\n\n");

        if(rearDistance > agent.getLength()*2 && frontDistance > 200) {
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
