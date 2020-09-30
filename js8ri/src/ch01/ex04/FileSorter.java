package ch01.ex04;

import java.io.File;
import java.util.Arrays;

/**
 * ディレクトリの同一階層に存在するディレクトリ及びファイルのグループそれぞれでソートする
 */
public class FileSorter {

    public static void sort(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            if (f1.isDirectory() && !f2.isDirectory()) {
                return -1;
            } else if (!f1.isDirectory() && f2.isDirectory()) {
                return 1;
            } else {
                return f1.getPath().compareTo(f2.getPath());
            }
        });
    }

    private static void printFiles(File[] files) {
        Arrays.stream(files).forEach(System.out::println);
    }


    public static void main(String[] args) {
        File file = new File("C:\\study\\java\\java-learning\\js8ri");
        File[] files = file.listFiles();
        FileSorter.printFiles(files);
        FileSorter.sort(files);
        System.out.println();
        FileSorter.printFiles(files);
    }
}
