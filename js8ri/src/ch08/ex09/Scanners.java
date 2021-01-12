package ch08.ex09;

import java.util.*;
import java.util.stream.*;

public class Scanners {
    static Stream<String> wordStream(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }

            @Override
            public String next() {
                return scanner.next();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    static Stream<String> lineStream(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    static IntStream intStream(Scanner scanner) {
        return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(new PrimitiveIterator.OfInt() {
            @Override
            public int nextInt() {
                return scanner.nextInt();
            }

            @Override
            public boolean hasNext() {
                return scanner.hasNextInt();
            }
        }, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    static DoubleStream doubleStream(Scanner scanner) {
        return StreamSupport.doubleStream(Spliterators.spliteratorUnknownSize(new PrimitiveIterator.OfDouble() {
            @Override
            public double nextDouble() {
                return scanner.nextDouble();
            }

            @Override
            public boolean hasNext() {
                return scanner.hasNextInt();
            }
        }, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static void main(String[] args) {

        System.out.println("wordStream:   " + wordStream(createScanner()).collect(Collectors.joining(" / ")));
        System.out.println("lineStream:   " + lineStream(createScanner()).collect(Collectors.joining(" / ")));
        System.out.println("intStream:    " + intStream(createScanner()).mapToObj(Integer::toString).collect(Collectors.joining(" / ")));
        System.out.println("doubleStream: " + doubleStream(createScanner()).mapToObj(Double::toString).collect(Collectors.joining(" / ")));
    }

    static Scanner createScanner() {
        String b = System.lineSeparator();
        Scanner scanner = new Scanner("1 2 3 4 5" + b + "6 7 8 9 10" + b);
        return scanner;
    }

}
