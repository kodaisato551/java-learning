package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ウェブページ の 内容 を 読み込ん で、
 * ファイル に 保存 する プログラム を 作成 し なさい。
 */
public class WebPageSaver {
    static void save(String inputURI, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        URI u = URI.create(inputURI);
        try (InputStream in = u.toURL().openStream()) {
            Files.copy(in, path);
        }
    }

    public static void main(String[] args) throws IOException {
        save("http://www.google.com/", "C:\\study\\java-learning\\js8ri\\src\\ch09\\ex07\\save.html");
    }
}
