package ch01.ex03;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameSearcher {
    private final String filePath;

    private FileNameSearcher(String filePath) {
        this.filePath = filePath;
    }

    private void showSubDirec(FilenameFilter fileFilter) {
        File file = new File(filePath);
        File[] subFiles = file.listFiles(fileFilter);
        if (subFiles != null) {
            for (File f : subFiles) {
                System.out.println(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        FileNameSearcher searcher = new FileNameSearcher("C:\\study\\java\\java-learning\\js8ri");
        searcher.showSubDirec(
                (f, s) -> s.contains(".iml")
        );

    }
}

