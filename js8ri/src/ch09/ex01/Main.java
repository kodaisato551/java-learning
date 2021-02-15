package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
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
        } finally {
            in.close();
        }

        try {
            while (in.hasNext()) out.println(in.next().toLowerCase());
        } finally {
            in.close();
            out.close();
        }

    }
}
