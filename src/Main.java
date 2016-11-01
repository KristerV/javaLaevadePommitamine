import javafx.application.Application;
import javafx.stage.Stage;

// Mängus on "peidus" laev tumesinine, testimise eesmärgil.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int mituLaeva = 50;
        new Meri(mituLaeva);

    }
}