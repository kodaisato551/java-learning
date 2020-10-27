package ch02.ex13;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ShortWordFinder {
    private static final int TH = 10;

    public Map<Integer, Long> find(List<String> words) {
        AtomicInteger[] counts = new AtomicInteger[TH];
        return words.parallelStream().filter(str -> str.length() < TH).collect(Collectors.groupingBy(String::length, Collectors.counting()));
    }
}
