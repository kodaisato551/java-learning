package ch01.ex01;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 呼び出したスレッド
 */
public class CheckArraysSortThread {
    private static String[] target = {"aaaa", "aaa", "abcde", "123456", "x", "00"};
    static Comparator<String> checker = (s1, s2) -> {
        System.out.println("Called thread: " + currentThreadInfo());
        return Integer.compare(s1.length(), s2.length());
    };

    private static String currentThreadInfo() {
        Thread current = Thread.currentThread();
        return "name=" + current.getName() + ", id=" + current.getId();
    }

    public static void main(String[] args) {
        System.out.println("Caller thread: " + currentThreadInfo());
        Arrays.sort(target, checker);
    }
}
