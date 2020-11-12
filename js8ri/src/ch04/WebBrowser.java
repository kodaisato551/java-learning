package ch04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * WebViewer を使用して、URL バーと戻るボタンを持つブラウザを実装しなさい。ヒント：WebEngine.getHistory()
 */
public class WebBrowser extends Application {
    String url = "https://www.google.com/?hl=ja";

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        TextField linkField = new TextField(url);
        linkField.setPrefWidth(300);
        Button backButton = new Button("Back");
        HBox hBox = new HBox(linkField,backButton);
        pane.setTop(hBox);

        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        engine.load(url);
        pane.setCenter(browser);

        //リスナー登録
        backButton.setOnAction(
                actionEvent -> {
                    engine.getHistory().go(-1);
                }
        );

        engine.locationProperty().addListener(
                (location, oldLocation, newLocation) -> { linkField.setText(newLocation); }
        );

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
