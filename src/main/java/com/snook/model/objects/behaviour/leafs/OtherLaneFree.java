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
        int x = (drivingRightLane(agent))? agent.getX()-1 : agent.getX()+1;

        SimulationObject frontClosestObject = getRowClosestObject(x, agent.getY(), board, agent.getDirection());
        SimulationObject rearClosestObject = getRowClosestObject(x, agent.getY(), board, SOUTH);

        if(  (frontClosestObject == null && rearClosestObject == null)) {
            succeed();
            return;
        }
        int rearDistance = 9000, frontDistance = 9000;
        if(rearClosestObject != null) {
            rearDistance = (agent.getY() == rearClosestObject.getY()) ? 0 : Math.abs(calculateRowDifference(agent.getY(), rearClosestObject.getY(), board.getPixelHeight(), SOUTH));
        }
        if(frontClosestObject != null) {
            frontDistance = (agent.getY() == frontClosestObject.getY()) ? 0 : calculateRowDifference(agent.getY(), frontClosestObject.getY(), board.getPixelHeight(), agent.getDirection());

        }


        if(rearDistance > agent.getLength()*2 && frontDistance > 200) {
            succeed();
            return;
        }
       fail();
        return;
    }
}
