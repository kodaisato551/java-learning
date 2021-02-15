package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {
    /**
     * ファイル から すべて の 文字 を 読み込み、逆順 に 書き出す
     */
    public static void reverse(String inputPath, String outPath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(inputPath));
        String reversed = new StringBuilder(new String(bytes)).reverse().toString();
        Files.write(Paths.get(outPath), reversed.getBytes());
    }


    public static void main(String[] args) throws IOException {
        reverse("C:\\study\\java-learning\\js8ri\\src\\ch09\\ex06\\in.txt", "C:\\study\\java-learning\\js8ri\\src\\ch09\\ex06\\out.txt");
    }

}
