package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * 4.5 節「バインディング」のプログラムについて、円が真ん中に配置され、 シーンの4 つの辺の少なくとも2
 * つの辺に常に接するように機能拡張しなさい。
 */
public class CircleAdjustApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.BEIGE);
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));

        stage.setScene(scene);
        stage.show();
    }
}
