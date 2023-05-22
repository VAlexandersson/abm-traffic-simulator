package com.snook.model.objects.behaviour.composites;


import com.snook.model.objects.behaviour.Rule;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;

public class Repeater extends Rule {

    private final Rule rule;
    private int times;
    private int originalTimes;

    public Repeater(Rule rule) {
        super();
        this.rule = rule;
        this.times = -1; // infinite
        this.originalTimes = times;
    }

    public Repeater(Rule rule, int times) {
        super();
        if (times < 1) {
            throw new RuntimeException("Can't repeat negative times.");
        }
        this.rule = rule;
        this.times = times;
        this.originalTimes = times;
    }

    @Override
    public void start() {
        super.start();
        this.rule.start();
    }

    public void reset() {
        this.times = originalTimes;
    }

    @Override
    public void act(SimulationAgent agent, Board board) {
        if (rule.isFailure()) {
            fail();
            System.out.println("Repeater failed");
        } else if (rule.isSuccess()) {
            if (times == 0) {
                succeed();
                return;
            }
            if (times > 0 || times <= -1) {
                times--;
                rule.reset();
                rule.start();
            }
        }
        if (rule.isRunning()) {
            rule.act(agent, board);
        }
    }

    public Rule getRule() {
        return rule;
    }



    public Selector getStateOfNextRule() {
        if (rule instanceof Selector) {
            return (Selector) rule;
        }
        return null;
    }
}