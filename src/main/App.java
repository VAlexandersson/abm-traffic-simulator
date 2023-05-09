
import com.snook.model.Road;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static com.snook.view.TestView.printSimulation;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Simulation simulation = new Simulation(new Road(10, 4));
        simulation.addTruckAgent(2, 4, 0);
        simulation.addCarAgent(1, 0, 1);
        simulation.addObstacle(6, 1);
        
        while (true) {
            printSimulation(simulation);
            System.in.read();
            simulation.step();
        }
    }

    public static void main(String[] args) { launch(); }

}
