package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

/**
 * みなさん の コンピュータ では、 Arrays. parallelSort は、 配列 が どの くらいの 大き さで あれ ば Arrays. sort より 速く なり ます か。
 */
public class Test {

    private static int[] create(int size) {
        int[] array = new int[size];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt();
        }
        return array;
    }

    static long sortTimeWithParallel(int[] array) {
        long start = System.currentTimeMillis();
        Arrays.parallelSort(array);
        long end = System.currentTimeMillis();
        return end - start;
    }

    static long sortTimeWithNormal(int[] array) {
        long start = System.currentTimeMillis();
        Arrays.sort(array);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static int getArraysize() {
        int size = 100000;
        long parallelTime;
        long normalTime;
        do {
            size = size + 1;
            int[] array = create(size);
            parallelTime = sortTimeWithParallel(array);
            normalTime = sortTimeWithNormal(array);
            System.out.println("parallel : " + parallelTime);
            System.out.println("normal   : " + normalTime);

        } while (parallelTime > normalTime);

        return size;
    }

    public static void main(String[] args) {
        int size = getArraysize();
        System.out.println(size);
    }
}
