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
            agent.updatePosition();
            agent.setDirection(direction);
            succeed();
            return;
        }
        fail();
    }
}
