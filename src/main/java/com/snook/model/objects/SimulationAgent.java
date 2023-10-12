package com.snook.model.objects;

import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.behaviour.decorators.Repeater;
import com.snook.model.objects.core.Direction;
import com.snook.model.objects.core.ObjectState;

import java.util.concurrent.BrokenBarrierException;

public class SimulationAgent extends SimulationObject implements Agent, Runnable {


    private final Observer observer;
    private Rule rule;

    private int acceleration = 0;
    private int maxAcceleration;

    private double reactionTime;

    public SimulationAgent(String name, int x, int y, int width, int height, Type type, Observer observer) {
        super(name, x, y, width, height, type);
        this.observer = observer;
        maxAcceleration = 6;
        velocity = 15;
        maxVelocity = 30;
    }

    @Override // step()
    public void update() {
        if(rule == null) { return; }
        if (rule.getState() == null) { rule.start(); }
        do {
            rule.act(this, board);
        } while(getRepeater().getRule().getState() != Rule.RuleState.Success);
    }
    @Override
    public void setRule(Rule rule) {
        this.rule = rule;
    }
    @Override
    public void run() {
        try {
            update();
            observer.agentChanged(this);
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMaxAcceleration() {
        return maxAcceleration;
    }
    public void setMaxAcceleration(int maxAcceleration) { this.maxAcceleration = maxAcceleration; }
    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }
    public int getAcceleration() {
        return acceleration;
    }

    public void setReactionTime(double t) { this.reactionTime = t; }
    public double getReactionTime() { return reactionTime; }

    public void updateVelocity() {
        if(velocity + acceleration > 0) {
            if(velocity + acceleration > maxVelocity) {
                velocity = maxVelocity;
            } else {
                velocity += acceleration;
            }
        } else {
            velocity = 0;
            status = ObjectState.WAITING;
        }
    }

    public void updatePosition() {
        updateVelocity();

        if(status == ObjectState.STOPPED) { return; }

        switch (direction) {
            case NORTH -> setYModulo(getY() - velocity);
            case SOUTH -> setYModulo(getY() + velocity);
            case WEST -> setXModulo(getX() - velocity);
            case EAST -> setXModulo(getX() + velocity);
            case NORTH_EAST -> {
                setXModulo(getX() + 1);
                setYModulo(getY() - velocity);
                direction = Direction.NORTH;
            }
            case NORTH_WEST -> {
                setXModulo(getX() - 1);
                setYModulo(getY() - velocity);
                direction = Direction.NORTH;
            }
            case SOUTH_EAST -> {
                setXModulo(getX() + velocity);
                setYModulo(getY() + velocity);
                direction = Direction.SOUTH;
            }
            case SOUTH_WEST -> {
                setXModulo(getX() - velocity);
                setYModulo(getY() + velocity);
                direction = Direction.SOUTH;
            }
        }
        if(status == ObjectState.BUSY) { status = ObjectState.IDLE; }
        else { status = ObjectState.BUSY; }
    }

    private Repeater getRepeater() {
        if (rule instanceof Repeater) {
            return (Repeater) rule;
        }
        return null;
    }
}

