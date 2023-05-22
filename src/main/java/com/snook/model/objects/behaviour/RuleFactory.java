package com.snook.model.objects.behaviour;

import com.snook.model.objects.behaviour.composites.*;
import com.snook.model.objects.behaviour.decorators.*;
import com.snook.model.objects.behaviour.leafs.*;
import com.snook.model.objects.core.Direction;

public class RuleFactory {
    public static Rule repeater(Rule rule) { return new Repeater(rule); }
    public static Rule selector(Rule... rules) {
        Selector selector = new Selector();
        for (Rule rule : rules) {
            selector.addRule(rule);
        }
        return selector;
    }
    public static Rule sequence(Rule... rules) {
        Sequence sequence = new Sequence();
        for (Rule rule : rules) {
            sequence.addRule(rule);
        }
        return sequence;
    }

    public static Rule inverter(Rule rule) { return new Inverter(rule); }

    public static Rule goDirection(Direction direction) { return new GoDirection(direction); }
    public static Rule goNorth() { return new GoDirection(Direction.NORTH); }

    public static Rule objectInFront() { return new ObjectInFront(); }
    public static Rule shouldBrake() { return new ShouldBrake(); }

    public static Rule increaseAcceleration() { return new IncreaseAcceleration(); }

    private static Rule otherLaneFree() {
        return new OtherLaneFree();
    }

    private static Rule changeLane() {
        return new ChangeLane();
    }

    public static Rule overTakeOrFollowSelector() {
        return selector(
                otherLaneFree()
                // overTakeSequence(),
                // followSequence()
        );
    }

    private static Rule overTakeSequence() {
        return sequence(
                otherLaneFree(),
                changeLane()
        );
    }

    public static Rule speedUpSeq() {
        return sequence(
                increaseAcceleration(),
                goNorth()
        );
    }

    public static Rule slowDownSeq() {
        return sequence(
                objectInFront(),
                shouldBrake(),
                goNorth()
        );
    }


    public static Rule brain() {
        System.out.println("Brain");
        return repeater(
                        selector(
                                sequence(
                                objectInFront(),
                                        selector(
                                                overTakeSequence(),
                                                sequence(
                                                        shouldBrake(),
                                                        goNorth()
                                                )
                                        )
                                ),
                                speedUpSeq(),
                                goDirection(Direction.NORTH)
                        )
        );
    }

    public static Rule brainV2() {
        System.out.println("Brain");
        return repeater(selector(
                        slowDownSeq(),
                        speedUpSeq(),
                        goDirection(Direction.NORTH)

//                        sequence(
//                                objectInFront(),
//                                shouldBrake(),
//                                goNorth()
//                        ),
                )
        );
    }

}
