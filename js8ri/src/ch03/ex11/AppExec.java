package ch03.ex11;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;

public class AppExec extends Application {

    private final String imageFilePath = "C:\\study\\java\\java-learning\\js8ri\\src\\ch03\\ex11\\sea.jpg";
    private final String imageFramedFilePath = "C:\\study\\java\\java-learning\\js8ri\\src\\ch03\\ex11\\framed_sea.jpg";

    @Override
    public void start(Stage stage) throws Exception {
        final int frameSize = 50;
        Image in = new Image(new File(imageFilePath).toURI().toString());
        ColorTransformer transformer = (x, y, color) -> {
            if (ImageProcessor.isFrame(in, x, y, frameSize)) {
                return color;
            }
            return in.getPixelReader().getColor(x, y);
        };
        Image out = ImageProcessor.transform(in, transformer);
        ImageIO.write(SwingFXUtils.fromFXImage(out, null), "PNG", new File(imageFramedFilePath));
        System.exit(0);
    }
}
