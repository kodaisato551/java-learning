package ch01.ex02;

import java.io.File;
import java.io.FileFilter;

/**
 * 指定したディレクトリのサブディレクトリを返すメソッドを記載
 * FileFilterオブジェクトではなくラムダとメソッド参照を使用する
 */
public class SubDirectorySearcher {

    private final String filePath;

    private SubDirectorySearcher(String filePath) {
        this.filePath = filePath;
    }

    private void showSubDirec(FileFilter fileFilter) {
        File file = new File(filePath);
        File[] subFiles = file.listFiles(fileFilter);
        if (subFiles != null) {
            for (File f : subFiles) {
                System.out.println(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        SubDirectorySearcher searcher = new SubDirectorySearcher("C:\\study\\java\\java-learning\\js8ri");
        searcher.showSubDirec(File::isDirectory);
        System.out.println();
        searcher.showSubDirec((f) -> f.isDirectory());
    }
}
