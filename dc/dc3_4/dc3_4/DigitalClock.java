package dc3_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * TODO Update ui. For scalability, should have properties apart from this class.
 */
public class DigitalClock extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("clock.fxml"));
        Scene scene = new Scene(pane, 300, 200);
        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
