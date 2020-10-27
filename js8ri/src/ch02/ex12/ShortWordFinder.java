package ch02.ex12;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortWordFinder {
    private static final int TH = 10;

    public int[] find(List<String> words) {
        AtomicInteger[] counts = new AtomicInteger[TH];
        words.parallelStream().forEach(str ->
        {
            if (str.length() < TH) {
                counts[str.length()].getAndIncrement();
            }
        });
        int[] retValue = new int[TH];
        for (int i = 0; i < TH; i++) {
            retValue[i] = counts[i].get();
        }
        return retValue;
    }
}
