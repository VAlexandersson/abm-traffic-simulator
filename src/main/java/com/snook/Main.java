package com.snook;

import com.snook.model.SimulationEngine;
import com.snook.model.board.Board;
import com.snook.model.objects.SimulationObject;
import com.snook.view.ViewPanel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        Board board = new Board("src\\main\\resources\\board.txt");
        ArrayList<SimulationObject> objects = new ArrayList<>();

        SimulationEngine simulationEngine = new SimulationEngine(objects, board);
        ViewPanel view = new ViewPanel();
        view.setPreferredSize(new Dimension(board.getPixelHeight(), board.getPixelWidth()));

        SimulationPresenter simulationPresenter = new SimulationPresenter(simulationEngine, view);

        simulationPresenter.addAgent(4, 200);
        simulationPresenter.addAgent(4, 400);
        simulationPresenter.addAgent(4, 700);

        JFrame frame = new JFrame("Traffic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(view);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        simulationPresenter.startSimulation();
    }
}