public class VehicleAgent {
    private int id;
    private int x;
    private int y;
    private CellState type;

    public VehicleAgent(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public int getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public CellState getType() {
        return this.type;
    }
}
