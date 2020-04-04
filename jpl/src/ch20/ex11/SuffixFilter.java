package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFilter {
    public static void showFilteredList(File dir, String suffix) {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException();
        }

        FilenameFilter filenameFilter = (f, s) -> s.endsWith(suffix);
        File[] list = dir.listFiles(filenameFilter);
        System.out.println("Dir: " + dir.getName() + " (suffix : " + suffix + ")");
        for (File file : list) {
            System.out.println("- " + file.getName());
        }

    }

    public static void main(String[] args) {
        String pass = "C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex11";
        showFilteredList(new File(pass), "txt");
    }

}
