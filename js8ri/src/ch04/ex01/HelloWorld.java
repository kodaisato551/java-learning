package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label message = new Label("Hello, FX!");
        message.setFont(new Font(100));
        TextField textField = new TextField("Hello, FX!");
        message.textProperty().bind(textField.textProperty());
        BorderPane pane = new BorderPane();
        pane.setBottom(textField);
        pane.setCenter(message);
        stage.setScene(new Scene(pane));
        stage.setTitle("Hello");
        stage.show();
    }
}
