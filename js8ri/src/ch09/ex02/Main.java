package ch09.ex02;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static void smallWord() throws IOException {
        try (Scanner in = new Scanner(Paths.get("/usr/share/dict/words"));
             PrintWriter out = new PrintWriter("/tmp/out.txt")) {
            while (in.hasNext()) out.println(in.next().toLowerCase());
        }
    }

    static void smallWord_noTryWithRes() throws IOException {
        Scanner in = new Scanner(Paths.get("usr/shar/dict/words"));
        PrintWriter out;
        try {
            out = new PrintWriter("/tmp/out.txt");
        } catch (IOException e) {
            close(in, e);
            throw e;
        }

        try {
            while (in.hasNext()) out.println(in.next().toLowerCase());
        } catch (NoSuchElementException | IllegalStateException e) {
            close(in, e);
            close(out, e);
            throw e;
        }

        try {
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void close(Closeable closeable, Exception e) {
        try {
            closeable.close();
        } catch (IOException e1) {
            e.addSuppressed(e1);
        }
    }
}
