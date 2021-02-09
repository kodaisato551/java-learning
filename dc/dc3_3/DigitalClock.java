import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DigitalClock extends Application {

    private final Label mLabel = new Label();
    private final Setting mSetting = Setting.getInstance();
    private BorderPane mPane;
    private Scene mScene;

    private final EventHandler<ActionEvent> mEventHandler = e -> {
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: " + currentTime);
        mLabel.setText(currentTime);
        Font font = new Font(mSetting.getFontStyle(), mSetting.getFontSize());
        mLabel.setFont(font);
        mPane.setBackground(new Background(new BackgroundFill(mSetting.getBgColor(), null, null)));
        mLabel.setTextFill(mSetting.getFontColor());
    };


    @Override
    public void start(Stage stage) throws Exception {

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000), mEventHandler)
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        mPane = new BorderPane();
        mPane.setCenter(mLabel);
        ContextMenu menu = new PropMenu(stage).getMenu();
        mPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> menu.show(mPane, e.getScreenX(), e.getScreenY()));
        Dimension2D window = mSetting.calcPreferredWindowSize();
        mScene = new Scene(mPane, window.getWidth(), window.getHeight());
        stage.setScene(mScene);
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
