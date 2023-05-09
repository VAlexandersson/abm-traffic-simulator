public class CarAgent extends VehicleAgent{
    private CellState type;
    private int length;
    public CarAgent(int id, int x, int y) {
        super(id, x, y);
        this.length = 1;
        this.type = CellState.CAR;
    }
    public CellState getType() {
        return this.type;
    }
}
