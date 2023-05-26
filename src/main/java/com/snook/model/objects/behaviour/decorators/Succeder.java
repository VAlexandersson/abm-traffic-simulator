package com.snook.model.objects.behaviour.decorators;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.behaviour.Rule;

public class Succeder extends Rule {

    Rule rule;

    public Succeder(Rule rule) {
        super();
        this.rule = rule;
    }

    @Override
    public void reset() {
        rule.reset();
    }

    @Override
    public void start() {
        super.start();
        rule.start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {
        if(rule.isFailure()) {
            succeed();
        } else if(rule.isSuccess()) {
            succeed();
        } else if(rule.isRunning()) {
            rule.act(agent, board);
        }
    }
}
