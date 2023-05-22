package com.snook.model.objects.behaviour.composites;

import com.snook.model.objects.behaviour.Rule;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationAgent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Selector extends Rule {

    public Selector() {
        super();
        this.currentRule = null;
    }

    private Rule currentRule;
    List<Rule> rules = new LinkedList<Rule>();
    Queue<Rule> ruleQueue = new LinkedList<Rule>();


    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public void reset() {
        for (Rule rule : rules) {
            rule.reset();
        }
    }

    @Override
    public void start() {
        // start the current sequence
        super.start();
        // reset the current queue and copy the rules from setup
        ruleQueue.clear();
        ruleQueue.addAll(rules);
        currentRule = ruleQueue.poll();
        currentRule.start();
    }

    @Override
    public void act(SimulationAgent agent, Board board) {

        currentRule.act(agent, board);
        // if is still running, then carry on
        if (currentRule.isRunning()) { return; }

        // check if the rule is successful and finish the sequence
        if (currentRule.isSuccess()) {
            succeed();
            return;
        }

        // We need to progress the sequence. If there are no more rules
        // then the state is the last rule's state. (Success for OR was already handled)
        if (ruleQueue.peek() == null) {
            this.state = currentRule.getState();
        } else {
            currentRule = ruleQueue.poll();
            currentRule.start();
        }
    }
}