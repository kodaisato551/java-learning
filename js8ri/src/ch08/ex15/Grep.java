package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Grep {
    static void showGrepped(String path, String pattern) throws IOException {
        Predicate<String> predicate = Pattern.compile(pattern).asPredicate();
        Files.lines(Paths.get(path)).filter(predicate).forEach(System.out::println);
    }
}
