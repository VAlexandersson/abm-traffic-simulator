package com.snook.model.objects.behaviour.composites;

import com.snook.model.objects.behaviour.Rule;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sequence extends Rule {

    public Sequence() {
        super();
        this.currentRule = null;
    }

    private Rule currentRule;
    List<Rule> rules = new LinkedList<>();
    Queue<Rule> ruleQueue = new LinkedList<>();

    public void addRule(Rule rule) { rules.add(rule); }

    @Override
    public void reset() { for (Rule rule : rules) { rule.reset(); } }


    @Override
    public void start() {
        super.start();
        ruleQueue.clear();
        ruleQueue.addAll(rules);
        currentRule = ruleQueue.poll();
        assert currentRule != null;
        currentRule.start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {

            currentRule.act(agent, board);

            if (currentRule.isRunning()) { return; }
            if (ruleQueue.peek() == null) {
                this.state = currentRule.getState();
                return;
            }
            if (currentRule.isFailure()) {
                fail();
                return;
            }

            currentRule = ruleQueue.poll();
            assert currentRule != null;
            currentRule.start();
    }
}
