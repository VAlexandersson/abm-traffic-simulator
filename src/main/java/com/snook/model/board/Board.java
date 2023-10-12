package com.snook.model.board;

import com.snook.model.objects.SimulationObject;
import com.snook.constants.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private int width;
    private int height;

    public final int scale = Constants.scale;

    private ArrayList<SimulationObject> simulationObjects = new ArrayList<SimulationObject>();
    private TileState[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new TileState[width][height];
    }
    public Board(String filePath) {
        loadEnviromentFromFile(filePath);
    }

    public Board copy() {
        Board copy = new Board(this.width, this.height);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                copy.setTileState(x, y, this.getTileState(x, y));
            }
        }
        return copy;
    }

    public TileState getTileState(int x, int y){
        return tiles[(x+width)%width][(y+height)%height];
    }
    public void setTileState(int x, int y, TileState state) {
        tiles[(x+width)%width][(y+height)%height] = state;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    private void loadEnviromentFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            height = getRowCount(filePath);
            String line = reader.readLine();
            this.width = line.length();

            this.tiles = new TileState[width][height];
            int y = 0;
            do{
                for (int x = 0; x < width; x++) {
                    char tileChar = line.charAt(x);
                    TileState tileType = determineTileType(tileChar);
                    tiles[x][y] = tileType;
                }
                y++;
            } while ((line = reader.readLine()) != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int getRowCount(String filePath) throws IOException {
        int rowCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                rowCount++;
            }
        }
        System.out.println(rowCount);
        return rowCount;
    }
    private TileState determineTileType(char tileChar) {
        switch (tileChar) {
            case '0':
                return TileState.EMPTY;
            case '1':
                return TileState.ROAD;
            case '2':
                return TileState.INTERSECTION;
            default:
                return TileState.EMPTY;
        }
    }

//    public TileState getTileStateScaled(int x, int y){
//        int xScaled = x / Constants.tileSize;
//        int yScaled = y / Constants.tileSize;
//        return tiles[x][y];
//    }
    public void addSimulationObject(SimulationObject simulationObject) {
        if (isTileWalkable(simulationObject.getX(), simulationObject.getY())) {
            simulationObjects.add(simulationObject);
            simulationObject.setBoard(this);
        }
    }
    public boolean isRoad(int x, int y) { return getTileState(x, y) == TileState.ROAD; }
    public boolean isTileWalkable(int x, int y) {
        if (!isRoad(x, y)) return false;
        for (SimulationObject simulationObject : simulationObjects) {
            if (simulationObject.getX() == x && simulationObject.getY() == y) {
                return false;
            }
        }
        return true;
    }
    public List<SimulationObject> getSimulationObjects() {
        return simulationObjects;
    }


    public int getScale() {
        return scale;
    }

    public int getPixelWidth() {
        return width * scale;
    }

    public int getPixelHeight() {
        return height * scale;
    }


    public void addSimulationObjects(SimulationObject... simulationObjects) {
        for (SimulationObject simulationObject : simulationObjects) {
            addSimulationObject(simulationObject);
        }
    }
}