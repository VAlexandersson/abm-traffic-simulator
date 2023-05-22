package com.snook.view;

import com.snook.model.objects.SimulationObject;

import javax.swing.*;

public class TextPane {
    private final JTextArea ta = new JTextArea(10, 20);

    public void printToPane(SimulationObject objects) {
        ta.append(objects.getType()+"{"+objects.getName()+"} ("+objects.getX()+","+objects.getY()+") W:"+objects.getWidth()+" L:"+objects.getLength()+"\n");
    }
}
