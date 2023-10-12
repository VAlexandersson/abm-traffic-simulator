package com.snook.model.objects.behaviour;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;

public abstract class Rule {
    public enum RuleState {
        Success,
        Failure,
        Running
    }

    protected RuleState state;

    protected Rule() { }

    public void start() {
        this.state = RuleState.Running;
    }

    public abstract void reset();

    public abstract void act(SimulationAgent agent, Board board);

    protected void succeed() {
        this.state = RuleState.Success;
    }

    protected void fail() {
        this.state = RuleState.Failure;
    }

    public boolean isSuccess() {
        return state.equals(RuleState.Success);
    }

    public boolean isFailure() {
        return state.equals(RuleState.Failure);
    }

    public boolean isRunning() {
        return state.equals(RuleState.Running);
    }

    public RuleState getState() {
        return state;
    }
}