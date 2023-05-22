package com.snook.model.objects.core;

public class Position {
    private int x;
    private int y;
    private int deltaX;
    private int deltaY;

    public Position(int x, int y, int deltaX, int deltaY) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Position(Position toBeCopied) {
        this(toBeCopied.x, toBeCopied.y, toBeCopied.deltaX, toBeCopied.deltaY);
    }


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.deltaX = 0;
        this.deltaY = 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getDeltaX() { return deltaX; }
    public int getDeltaY() { return deltaY; }

    public void setX(int x) {
        deltaX = x - this.x;
        this.x = x;
    }
    public void setY(int y) {
        deltaY = y - this.y;
        this.y = y;
    }

    public void moveNorth(int velocity) { y -= velocity; }
    public void moveSouth(int velocity) { y += velocity; }
    public void moveWest(int velocity) { x -= velocity; }
    public void moveEast(int velocity) { x += velocity; }

}
