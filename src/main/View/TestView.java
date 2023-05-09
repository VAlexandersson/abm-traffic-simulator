
import com.snook.Simulation;
import com.snook.model.CellState;
import com.snook.model.Road;

import java.io.IOException;

public class TestView {

    public static void printSimulation(Simulation simulation) {
        System.out.println("123456789");
        for (int y = 0; y < simulation.getRoad().getHeight(); y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < simulation.getRoad().getWidth(); x++) {
                if (simulation.getRoad().getState(x, y) == CellState.FREE) {
                    line.append(" ");
                } else if (simulation.getRoad().getState(x, y) == CellState.CAR) {
                    line.append("🚗");
                } else if (simulation.getRoad().getState(x, y) == CellState.TRUCK) {
                    line.append("🚛");
                } else if (simulation.getRoad().getState(x, y) == CellState.OBSTACLE) {
                    line.append("🪨");
                }
            }
            System.out.println(line);
        }
    }
}
