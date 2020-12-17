package ch06.ex10;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder content = new StringBuilder();
            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNext()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        });
    }

    static List<URL> getLinks(String content) {
        String linkPattern = "<a\\s+[^>]*href\\s*=\\s*\"([^\"]+)\"[^>]*>";
        Matcher matcher = Pattern.compile(linkPattern).matcher(content);
        List<URL> urls = new ArrayList<>();
        while (matcher.find()) {
            try {
                urls.add(new URL(matcher.group(1)));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    public static void main(String[] args) {
        System.out.print("Enter URL : ");
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            readPage(new URL(input))
                    .thenApply(Test::getLinks)
                    .thenAccept(urls -> {
                        System.out.println("The links are :");
                        urls.forEach(System.out::println);
                    });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }
}
