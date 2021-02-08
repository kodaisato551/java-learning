package dc3_4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ClockController implements Initializable {
    private final Label mLabel = new Label();

    private final EventHandler<ActionEvent> mEventHandler = e -> {
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: " + currentTime);
        mLabel.setText(currentTime);
        mLabel.setFont(new Font("Arial", 30));
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000), mEventHandler)
        );
        timer.play();
    }

    private static String getCurrentTime() {
        Date dateObj = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(dateObj);
    }


}
