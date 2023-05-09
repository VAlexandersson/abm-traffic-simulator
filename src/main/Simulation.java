import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<VehicleAgent> vehicleAgentList;
    private Road road;

    public Simulation(Road road){
        this.vehicleAgentList = new ArrayList<>();
        this.road = road;
    }

    public void addTruckAgent(int id, int x, int y) {
        vehicleAgentList.add(new TruckAgent(id, x, y));
        this.road.setState(x, y, CellState.TRUCK);
    }
    public void addCarAgent(int id, int x, int y) {
        vehicleAgentList.add(new CarAgent(id, x, y));
        this.road.setState(x, y, CellState.CAR);
    }
    public void addObstacle(int x, int y) {
        this.road.setState(x, y, CellState.OBSTACLE);
    }


    public void step(){
        for (VehicleAgent vehicleAgent : vehicleAgentList) {

            this.road.setState(vehicleAgent.getX(), vehicleAgent.getY(), CellState.FREE);
            if(checkForObjects(vehicleAgent)){
                vehicleAgent.setY(vehicleAgent.getY()+1);
            }
            vehicleAgent.setX((vehicleAgent.getX()+1)%this.road.getWidth());
            this.road.setState(vehicleAgent.getX(), vehicleAgent.getY(), vehicleAgent.getType());
        }
    }

    private void avoidObject() {

    }

    private boolean checkForObjects(VehicleAgent vehicleAgent) {
        for (int x = vehicleAgent.getX()+1; x < vehicleAgent.getX()+3; x++) {
            if(road.getState(x%this.road.getWidth(), vehicleAgent.getY()) == CellState.OBSTACLE) {
                return true;
            }
        }
        return false;
    }


}
