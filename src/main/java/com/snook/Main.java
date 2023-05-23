package com.snook;

import com.snook.model.Simulation;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationObject;
import com.snook.view.ViewPanel;

import java.util.ArrayList;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        Board board = new Board("src\\main\\resources\\board.txt");
        ArrayList<SimulationObject> objects = new ArrayList<>();

        Simulation simulation = new Simulation(objects, board);
        ViewPanel view = new ViewPanel(board);

        Simulator simulator = new Simulator(simulation, view);

        //simulator.addAgent(4,1100);
        simulator.addAgent(4, 450);
        simulator.addAgent(5, 500);
        simulator.addAgent(5, 1000);

        simulator.addObstacle(4, 100);
        simulator.addObstacle(5, 400);
        simulator.addObstacle(4, 700);





        //simulator.addRandomAgent();

        JFrame frame = new JFrame("Traffic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(view);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        simulator.startSimulation();
    }
}