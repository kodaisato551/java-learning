package ch20.ex07;

import java.io.*;

public class Main {
    private static final String pass = "C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex07\\in.txt";

    public static void main(String[] args) throws IOException {
        InputStream fin = new FileInputStream(new File(pass));
        DataInputStream in = new DataInputStream(fin);
        Attr attr = new Attr(in);
        System.out.println(attr.toString());
    }


}
