package dc3_1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO Update ui. For scalability, should have properties apart from this class.
 */
public class DigitalClock extends Application {

    private  Label mLabel = new Label();

    private final EventHandler<ActionEvent> mEventHandler = e->{
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: "+ currentTime);
        mLabel.setText(currentTime);
    };


    @Override
    public void start(Stage stage) throws Exception {

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000),mEventHandler)
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        stage.setScene(new Scene(mLabel, 100, 100));
        stage.show();
    }


    private static String getCurrentTime(){
        Date dateObj = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        return format.format( dateObj );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
