package com.snook.model.objects.behaviour.leafs;

import com.snook.model.board.Board;
import com.snook.model.board.TileState;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.SimulationObject;
import com.snook.model.objects.core.Direction;

import java.util.Random;

public class Helper {

    private static final double REACTION_TIME_MIN = 0.5;
    private static final int SAFE_DISTANCE_TIME_RULE = 2;
    private static final Random random = new Random();


    public static double getBrakingDistance(int relativeVelocity, int maxAcceleration) {
        return ((double) (relativeVelocity * relativeVelocity) / (maxAcceleration*2));
    }
    public static double getRandomReactionTime() { return (random.nextDouble() + REACTION_TIME_MIN); }
    public static int getThresholdDistance(int velocity) {
        return (int) (velocity * getRandomReactionTime() * SAFE_DISTANCE_TIME_RULE);
    }


    public static int getDistanceFromClosestObject(SimulationAgent agent, String changeTo, Direction direction) {

        int roadLength = agent.getBoard().getPixelHeight();
        int currentRow = agent.getY();
        int lane = agent.getX();

        if(changeTo.equals("left")) {
            lane--;
            currentRow += agent.getLength()*3;
        }
        else if(changeTo.equals("right")) {
            lane++;
            currentRow += agent.getLength()*3;
        }
        else if(changeTo.equals("straight")) { lane = lane; }

        SimulationObject object = getRowClosestObject(lane, currentRow, agent.getBoard(), direction);
        int otherRow = object.getY();

        switch (direction){
            case NORTH -> {
                otherRow = (otherRow + object.getLength())%roadLength;
                if(currentRow >= otherRow) {
                    return currentRow-otherRow;
                }
                return (roadLength - otherRow) + currentRow;
            }
            case SOUTH -> {
                currentRow = (currentRow + agent.getLength())%roadLength;
                if(otherRow >= currentRow) {
                    return otherRow-currentRow;
                }
                return (roadLength - currentRow) + otherRow;
            }
        }
        return -1;
    }

    public static int calculateRelativeDistance(SimulationAgent agent, SimulationObject object) {
        Direction direction = agent.getDirection();
        int boardHeight = agent.getBoard().getPixelHeight();
        int currentRow = agent.getY();
        int otherRow = object.getY();

        switch (direction){
            case NORTH -> {
                otherRow = (otherRow + object.getLength())%boardHeight;
                if(currentRow >= otherRow) {
                    return currentRow-otherRow;
                }
                return (boardHeight - otherRow) + currentRow;
            }
            case SOUTH -> {
                currentRow = (currentRow + agent.getLength())%boardHeight;
                if(otherRow >= currentRow) {
                    return otherRow-currentRow;
                }
                return (boardHeight - currentRow) + otherRow;
            }
        }
        return 1;
    }

    public static int calculateRowDifference(int currentRow, int otherRow, int boardHeight, Direction direction) {
        switch (direction){
            case NORTH -> {
                if(currentRow >= otherRow) {
                    return currentRow-otherRow;
                }
                return (boardHeight - otherRow) + currentRow;
            }
            case SOUTH -> {
                if(currentRow <= otherRow) {
                    return currentRow-otherRow;
                }
                return (currentRow >= otherRow) ? currentRow - otherRow : currentRow + (boardHeight - otherRow);
            }
        }
        int rowDiff = Math.abs(currentRow - otherRow);
        return  Math.min(rowDiff, boardHeight - rowDiff);
    }
    public static int getRelativeSpeed(int velocity, int otherVelocity) {
        return velocity - otherVelocity;
    }

    public static SimulationObject getRowClosestObject(int x, int y, Board board, Direction direction) {
        SimulationObject closestObject = null;
        int minRowDifference = 9000;
        int currObjDiff;
        for (SimulationObject object : board.getSimulationObjects()) {
            if(object.getY() == y) continue;
            if(object.getX() != x) continue;
            currObjDiff = calculateRowDifference(y, object.getY(), board.getPixelHeight(), direction);
            if(currObjDiff < minRowDifference) {
                closestObject = object;
                minRowDifference = currObjDiff;
            }
        }
        return closestObject;
    }

    public static SimulationObject getRowClosestObjectV2(SimulationAgent agent) {
        SimulationObject closestObject = null;
        int minRowDifference = 9000;
        int currObjDiff;

        for (SimulationObject object : agent.getBoard().getSimulationObjects()) {
            if(agent.getName() == object.getName()) continue;
            if(object.getX() != agent.getX()) continue;
            currObjDiff = calculateRelativeDistance(agent, object);
            if(currObjDiff < minRowDifference) {
                closestObject = object;
                minRowDifference = currObjDiff;
            }
        }
        return closestObject;
    }

    public static boolean drivingRightLane(SimulationAgent agent) {
        return agent.getBoard().getTileState(agent.getX()+1, agent.getY()) == TileState.EMPTY;
    }

}
