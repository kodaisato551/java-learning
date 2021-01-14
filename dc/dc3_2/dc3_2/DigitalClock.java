package dc3_2;

import dc3_2.setting.CurrentSetting;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ウインドウのリサイズはできませんでした。
 */
public class DigitalClock extends Application {

    private final Label mLabel = new Label();
    private final MenuBar mMenuBar = new MenuBar();
    private final Menu mMenu = new Menu("Setting");
    private final MenuItem mMenuItem = new MenuItem("Properties");

    private final CurrentSetting mCurrentSetting = CurrentSetting.getInstance();

    public double mWindowHeight;
    public double mWindowWidth;
    private Dimension2D mWindowDimension;
    private DoubleProperty mProperty;

    private Scene mScene;
    private AnchorPane mPane;
    private Stage mStage;
    private VBox root;

    private final EventHandler<ActionEvent> mEventHandler = e -> {
        String currentTime = getCurrentTime();
        System.out.println("Current Time :: " + currentTime);
        mLabel.setText(currentTime);
//        Font font = new Font(mCurrentSetting.getFontStyle(), mCurrentSetting.getFontSize());
//        mLabel.setFont(font);
//        mLabel.setBackground(new Background(new BackgroundFill(mCurrentSetting.getBgColor(), null, null)));
//        mLabel.setTextFill(mCurrentSetting.getFontColor());
//        setCalculatedWindowSize(currentTime, font);
//
//        //window
//        mProperty = new SimpleDoubleProperty(mCurrentSetting.getFontSize());
//        mProperty.bind(mScene.widthProperty().add(mScene.heightProperty()).divide(50));
//
//        mLabel.setMaxHeight(mScene.getHeight());
//        mLabel.setMaxWidth(mScene.getWidth());
        adaptSetting();
    };

    private final EventHandler<ActionEvent> mPopUpPropDialog = e -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialog_ui.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };

    @Override
    public void start(Stage stage) throws Exception {

        configureLayout(stage);
        mMenuItem.setOnAction(mPopUpPropDialog);
        mStage = stage;
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(1000), mEventHandler)
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        stage.show();
    }

    private void configureLayout(Stage stage) {
        root = new VBox(2.0);
        mMenu.getItems().addAll(mMenuItem);
        mMenuBar.getMenus().addAll(mMenu);
        root.getChildren().add(mMenuBar);
        root.setSpacing(10);

        mPane = new AnchorPane();
        adaptSetting();
        mPane.getChildren().add(mLabel);
        mPane.autosize();
        root.getChildren().add(mPane);

        mScene = new Scene(root, mWindowWidth, mWindowHeight);
        stage.setScene(mScene);
        stage.setResizable(false);
    }

    private void adaptSetting() {
        Font font = new Font(mCurrentSetting.getFontStyle(), mCurrentSetting.getFontSize());
        mLabel.setFont(font);
        root.setBackground(new Background(new BackgroundFill(mCurrentSetting.getBgColor(), null, null)));
        mLabel.setTextFill(mCurrentSetting.getFontColor());
        setCalculatedWindowSize(font);
        mLabel.setMaxSize(mWindowWidth, mWindowHeight);
    }


    public void setCalculatedWindowSize(Font myFont) {
        Text text = new Text("yyyy/MM/dd HH:mm:ss");
        text.setFont(myFont);
        Bounds tb = text.getBoundsInLocal();
        Rectangle stencil = new Rectangle(
                tb.getMinX(), tb.getMinY(), tb.getWidth(), tb.getHeight()
        );

        Shape intersection = Shape.intersect(text, stencil);

        Bounds ib = intersection.getBoundsInLocal();
        System.out.println(
                "Text size: " + ib.getWidth() + ", " + ib.getHeight()
        );
        setWindowSize(ib.getWidth(), ib.getHeight());
    }

    private void setWindowSize(double fontWidth, double fontHeight) {
        mWindowWidth = fontWidth + 100;
        mWindowHeight = fontHeight + 50;
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
