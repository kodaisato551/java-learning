package dc3_2;

import dc3_2.setting.DefaultProperties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DigitalClock extends Application {

    private final Label mLabel = new Label();
    private final MenuBar mMenuBar = new MenuBar();
    private final Menu mMenu = new Menu("Setting");
    private final MenuItem mMenuItem = new MenuItem("Properties");

    private final EventHandler<ActionEvent> mEventHandler = e -> {
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: " + currentTime);
        mLabel.setText(currentTime);
        mLabel.setFont(DefaultProperties.FONT);
    };

    private final EventHandler<ActionEvent> mPopUpPropDialog = e -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialog_ui.fxml"));
        try {
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent, 300, 200);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };

    @Override
    public void start(Stage stage) throws Exception {

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000), mEventHandler)
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        configureLayout(stage);
        mMenuItem.setOnAction(mPopUpPropDialog);
        stage.show();
    }

    private void configureLayout(Stage stage) {
        mMenu.getItems().addAll(mMenuItem);
        mMenuBar.getMenus().addAll(mMenu);
        BorderPane root = new BorderPane();
        root.setTop(mMenuBar);
        root.setCenter(mLabel);
        stage.setScene(new Scene(root, 300, 100));
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
