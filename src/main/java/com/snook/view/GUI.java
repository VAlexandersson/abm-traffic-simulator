package com.snook.view;

import com.snook.model.objects.SimulationObject;

import javax.swing.*;
import java.awt.*;

public class GUI {
    int scale = 38; // ~ 1 cm
    int width;
    int height;

    private final JTextArea ta = new JTextArea(10, 20);

    public GUI() {
        JFrame frame = new JFrame();
        JPanel testPane = new JPanel(new BorderLayout());
        testPane.add(new JScrollPane(ta));
        frame.add(testPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void paintBoardComponent() {

    }

    public void update(String s) {
        if (!EventQueue.isDispatchThread()) {
            EventQueue.invokeLater(() -> update(s));
        } else {
            ta.append(s);
        }
    }

    public void update(SimulationObject object) {
        if (!EventQueue.isDispatchThread()) {
            EventQueue.invokeLater(() -> update(object));
        } else {
            ta.append(object.getType()+"{"+object.getName()+"} ("+object.getX()+","+object.getY()+") W:"+object.getWidth()+" L:"+object.getLength()+"\n");
        }
    }
}