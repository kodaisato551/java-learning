package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileProcessor {
    /**
     * ファイル から すべて の 行 を 読み込み、逆順 に 書き出す
     */
    public static void reverse(String inputPath, String outPath) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get(inputPath));
        Collections.reverse(strings);
        Files.write(Paths.get(outPath), strings);
    }


    public static void main(String[] args) throws IOException {
        reverse("C:\\study\\java-learning\\js8ri\\src\\ch09\\ex06\\in.txt", "C:\\study\\java-learning\\js8ri\\src\\ch09\\ex06\\out.txt");
    }

}
