public class TruckAgent extends VehicleAgent{
    private CellState type;
    private int length;
    public TruckAgent(int id, int x, int y) {
        super(id, x, y);
        this.length = 2;
        this.type = CellState.TRUCK;

    }

    public CellState getType() {
        return this.type;
    }
}
