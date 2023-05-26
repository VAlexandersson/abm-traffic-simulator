package com.snook.model.objects;

import com.snook.constants.Constants;
import com.snook.model.board.Board;
import com.snook.model.objects.core.Position;
import com.snook.model.objects.core.Direction;
import com.snook.model.objects.core.ObjectState;


public abstract class SimulationObject {

    public enum Type {
        OBJECT,
        OBSTACLE,
        AGENT,
        CAR,
        BLOCKAGE,
        TRAFFIC_LIGHT,
        INTERSECTION,
    }

    final String name;
    protected Position position;
    protected int width;
    protected int length;

    protected int velocity = 0;
    protected int maxVelocity;


    protected Type type;
    protected Direction direction = Direction.NORTH;
    protected ObjectState status = ObjectState.STOPPED;

    protected Board board;

    public SimulationObject(String name, int x, int y, int width, int height, Type type) {
        this.name = name;
        this.width = width * Constants.scale;
        this.length = height * Constants.scale;
        this.position = new Position(x, y);
        this.type = type;
    }

    public SimulationObject(SimulationObject toBeCopied) {
        this(toBeCopied.name, toBeCopied.getX(), toBeCopied.getY(), toBeCopied.getWidth(), toBeCopied.getLength(), toBeCopied.getType());
    }

    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    public String getName() {
        return name;
    }

    public int getX() { return position.getX(); }
    public int getY() { return position.getY(); }

    public void setX(int x) { this.position.setX(x); }
    public void setY(int y) { this.position.setY(y); }

    public void setDirection(Direction direction) { this.direction = direction; }
    public Direction getDirection() { return direction; }

    public void setVelocity(int velocity) { this.velocity = velocity; }
    public int getVelocity() { return velocity; }
    public int getMaxVelocity() { return maxVelocity; }
    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public int getWidth() {
        return width;
    }
    public int getLength() {
        return length;
    }

    public Type getType() { return type; }

    public ObjectState getState() { return status; }
    public void setState(ObjectState status) { this.status = status; }

    public void setXModulo(int x) { position.setX(( x+board.getPixelWidth())  % board.getPixelWidth()); }
    public void setYModulo(int y) { position.setY(( y+board.getPixelHeight()) % board.getPixelHeight()); }

}