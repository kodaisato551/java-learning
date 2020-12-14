package ch06.ex05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 複数スレッドが複数のファイルからすべての単語を読み込む
 * アプリケーションを書きなさい。
 * 各単語がどのファイルで使用されていたかを管理するために
 * ConcurrentHashMap<String,Set<File>>を使用しなさい。
 * マップを更新するために、mergeメソッドを使用しなさい
 */
public class Sample {
    static String[] read(File file) {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            return new String(bytes).split("[\\P{L}]+");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Map<String, Set<File>> usage(File[] files) {
        ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (File file : files) {
            service.submit(() -> {
                Set<File> fileSet = new HashSet<>();
                fileSet.add(file);
                String[] strs = read(file);
                for (String str : strs) {
                    map.merge(str, fileSet, (s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    });
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(15, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return map;
    }

    public static void main(String[] args) {
        File[] files = {new File("/Users/satokodai/Study/java-learning/js8ri/src/ch06/ex05/a.txt"),
                new File("/Users/satokodai/Study/java-learning/js8ri/src/ch06/ex05/b.txt")};
        Map<String, Set<File>> map = usage(files);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key : " + entry.getKey());
            System.out.println("elems : " + entry.getValue());

        }
    }
}
