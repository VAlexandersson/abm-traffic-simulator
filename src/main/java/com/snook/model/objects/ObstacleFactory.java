package com.snook.model.objects;

public class ObstacleFactory {
    static int id = 0;
    public static SimulationObstacle createObstacle(int x, int y) {
        return new SimulationObstacle("OBSTACLE" + id++, x, y, 1, 1, SimulationObject.Type.OBSTACLE);
    }
}
