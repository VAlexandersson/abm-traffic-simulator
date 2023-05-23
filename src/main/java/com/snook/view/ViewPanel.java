package com.snook.view;

import com.snook.model.board.Board;
import com.snook.model.board.TileState;
import com.snook.model.objects.SimulationObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ViewPanel extends JPanel {

    int scale, width, height;
    ArrayList<SimulationObject> simulationObjects;
    Board roadBoard;
    Board simulatorBoard;

    public ViewPanel(Board board) {
        roadBoard = board;
        height = board.getWidth();
        width = board.getHeight();
        scale = board.getScale();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width*scale, height*scale));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
        paintObjects(g);
    }

    private void paintObjects(Graphics g) {
        for (SimulationObject simulationObject : simulationObjects) {
            int xPos = simulationObject.getX();
            int yPos = simulationObject.getY();
            int width = simulationObject.getWidth();
            int height = simulationObject.getLength();
            if(simulationObject.getType() == SimulationObject.Type.AGENT)
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.RED);

            g.fillRect(yPos+1, xPos*scale+2, height-1, width-4);
        }

    }
    public void paintBoard(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int xPos = x * scale;
                int yPos = y * scale;
                TileState tileState = roadBoard.getTileState(y, x);
                switch (tileState) {
                    case ROAD ->{
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    }
                    default -> g.setColor(Color.GRAY);
                }
                g.fillRect(xPos, yPos, scale, scale);
            }
        }
    }

    public void updateObjects(ArrayList<SimulationObject> objects) {
        this.simulationObjects = objects;
        repaint();
    }

    public void printObjects(){
        if(simulationObjects != null)
            simulationObjects.forEach(object -> System.out.println(
                    object.getType()+"{"+object.getName()+"} ("+ object.getX()+","+object.getY()+") W:"+object.getWidth()+" L:"+object.getLength()));
    }

    public void updateSimulatorBoard(Board simulatorBoard) {
        this.simulatorBoard = simulatorBoard;
    }


    public void printToTerminal(ArrayList<SimulationObject> objects) {
        objects.forEach(object -> System.out.println(object.getType()+"{"+object.getName()+"} ("+object.getX()+","+object.getY()+") W:"+object.getWidth()+" L:"+object.getLength()));
    }

}
