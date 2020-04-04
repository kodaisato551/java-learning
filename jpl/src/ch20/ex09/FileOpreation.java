package ch20.ex09;

import java.io.File;
import java.io.IOException;

public class FileOpreation {
    private final String path;
    private final File file;

    public FileOpreation(String path) {
        this.path = path;
        file = new File(path);
    }

    public void showFileInfo() throws IOException {
        System.out.println("name           : " + file.getName());
        System.out.println("path           : " + file.getPath());
        System.out.println("parent path    : " + file.getParent());
        System.out.println("absolute path  : " + file.getAbsolutePath());
        System.out.println("canonical path : " + file.getCanonicalPath());
        System.out.println("last modified  : " + file.lastModified());
        System.out.println("length [byte]  : " + file.length());
    }

    public static void main(String[] args) throws IOException {
        FileOpreation fo = new FileOpreation("C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex09\\info.txt");
        fo.showFileInfo();
    }

}
