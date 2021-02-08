import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DigitalClock extends Application {

    private final Label mLabel = new Label();
    private final Setting mSetting = Setting.getInstance();

    private final EventHandler<ActionEvent> mEventHandler = e -> {
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: " + currentTime);
        mLabel.setText(currentTime);
        Font font = new Font(mSetting.getFontStyle(), mSetting.getFontSize());
        mLabel.setFont(font);
    };


    @Override
    public void start(Stage stage) throws Exception {

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000), mEventHandler)
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        BorderPane pane = new BorderPane();
        pane.setCenter(mLabel);
        ContextMenu menu = PropMenu.create();
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> menu.show(pane, e.getScreenX(), e.getScreenY()));
        Scene scene = new Scene(pane, 320, 240);
        stage.setScene(scene);
        stage.show();
    }


    private static String getCurrentTime() {
        Date dateObj = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(dateObj);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
