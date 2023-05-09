package com.snook.model;

public class Road {
    private int width;
    private int height;
    private int maxSpeed;

    private CellState[][] board;

    public Road(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new CellState[width][height];


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.setState(x, y, CellState.FREE);
            }
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }


    public void setState(int x, int y, CellState state) {
        this.board[x][y] = state;
    }

    public CellState getState(int x, int y){
        return this.board[x][y];
    }
}
