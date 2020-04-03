package ch20.ex05;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class WordFinder {
    private static final String FILE_LOC = "jpl\\src\\ch20\\ex05\\read.txt";
    private static final String KEYWORD = "one day";

    private static void fileReader() throws IOException {
        FileReader fileIn = new FileReader(FILE_LOC);
        LineNumberReader in = new LineNumberReader(fileIn);
        String str;

        while ((str = in.readLine()) != null) {
            if (str.contains(KEYWORD)) {
                str = in.getLineNumber() + " " + str;
            }
            System.out.println(str);
        }
        in.close();
    }

    public static void main(String[] args) throws IOException {
        fileReader();
    }

    private static String currentDir() {
        return new File(".").getAbsoluteFile().getParent();
    }
}



