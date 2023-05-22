package com.snook.model.objects.behaviour.decorators;

import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;
import com.snook.model.objects.behaviour.Rule;

public class Inverter extends Rule {

    private Rule rule;

    public Inverter(Rule rule) {
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
            System.out.println("-------- INVERTER - SUCCEEDED --------\n-------------------------");
            succeed();
        } else if(rule.isSuccess()) {
            System.out.println("-------- INVERTER - FAILED --------\n-------------------------");
            fail();
        } else if(rule.isRunning()) {
            System.out.println("-------- INVERTER - RUNNING --------\n-------------------------");
            rule.act(agent, board);
        }
    }
}