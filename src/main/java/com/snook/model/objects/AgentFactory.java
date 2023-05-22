package com.snook.model.objects;

import com.snook.constants.Constants;
import com.snook.model.objects.behaviour.Rule;
import com.snook.model.objects.behaviour.RuleFactory;
import com.snook.model.objects.behaviour.composites.Repeater;
import com.snook.model.objects.behaviour.composites.Sequence;
import com.snook.model.objects.behaviour.leafs.GoDirection;
import com.snook.model.objects.behaviour.leafs.ObjectInFront;
import com.snook.model.objects.behaviour.leafs.ShouldBrake;
import com.snook.model.objects.core.Direction;
import com.snook.model.objects.core.ObjectState;

import java.util.Random;

public class AgentFactory {
    private static final Random random = new Random();
    static private int id = 0;
    static private int index = 0;
    public static SimulationAgent createVehicleAgent() {
        return new SimulationAgent("MOVE" + id++, 4, 5, 1, 2, SimulationObject.Type.CAR, new Observer() {
            @Override
            public void agentChanged(SimulationObject object) {
                System.out.println("VehicleAgent.didChangeAgent " + index++ + object.getName() );
            }
        });
    }

    public static SimulationAgent createVehicleAgent(Observer observer) {
        return new SimulationAgent("MOVE" + id++, 4, 5, 1, 2, SimulationObject.Type.CAR, observer);
    }

    public static SimulationAgent createAgent(Observer observer, int x, int y) {
        SimulationAgent agent = new SimulationAgent("MOVE" + id++, x, y, 1, 2, SimulationObject.Type.AGENT, observer);
//        agent.setRule(new Repeater(new GoDirection(Direction.NORTH)));
        agent.setRule(RuleFactory.brain());

        //int maxVelocity = (random.nextInt(20 - 5 + 1) + 5);

        agent.setMaxVelocity((random.nextInt(20 - 5 + 1) + 5));
        agent.setMaxAcceleration((random.nextInt(10 - 4 + 1) + 4));


//        agent.setRule(RuleFactory.brainV2());
        agent.setVelocity((random.nextInt(agent.getVelocity()-5+1)+5));

        agent.setState(ObjectState.IDLE);
        agent.setReactionTime(random.nextDouble()+0.5);

        System.out.println("AgentFactory.createAgent " + agent.getName());
        return agent;
    }

    public static SimulationAgent createRandomAgent(Observer observer) {
        int maxspeed = random.nextInt(20 - 5 + 1) + 5;
        int y = random.nextInt(Constants.boardWidthInPixels + 1);
        int x = random.nextInt(5-4 + 1)+4;
        int maxAcceleration = random.nextInt(10 - 4 + 1) + 4;
        SimulationAgent agent = new SimulationAgent("MOVE" + id++, x, y, 1, 2, SimulationObject.Type.AGENT, observer);
        agent.setMaxVelocity(maxspeed);
        agent.setMaxAcceleration(maxAcceleration);
        agent.setRule(RuleFactory.brain());
        return agent;
    }

}
