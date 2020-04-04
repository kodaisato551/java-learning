package ch20.ex10;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class WordCounter {

    private WordCounter() {
    }

    public static HashMap<String, Integer> creatWordConunter(String path) throws IOException {
        HashMap<String, Integer> counter = new HashMap<>();

        try (FileReader fr = new FileReader(path)) {
            StreamTokenizer in = new StreamTokenizer(fr);
            while (in.nextToken() != StreamTokenizer.TT_EOF) {
                if (in.ttype == StreamTokenizer.TT_WORD) {
                    String word = in.sval;
                    counter.merge(word, 1, Integer::sum);
                }
            }
        }
        return counter;
    }


    public static void main(String[] args) throws IOException {

        final String pass = "C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex10\\in.txt";

        HashMap<String, Integer> map =
                WordCounter.creatWordConunter(pass);

        map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).forEach(System.out::println);
    }
}
