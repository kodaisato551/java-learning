package ch04.ex07;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Border extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TextField textField = new TextField("Border");
        textField.setBorder(new javafx.scene.layout.Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(4));
        pane.setCenter(textField);

        stage.setScene(new Scene(pane));
        stage.setTitle("hoge");
        stage.show();
    }
}
