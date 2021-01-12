package ch08.ex08;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheckedQueue {
    static void before() {
        Comparator comparator = (o1, o2) -> 1;
        Queue<Integer> queue = new PriorityQueue<>(comparator);
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue);

        Queue queue2 = queue;
        queue2.offer("three");
        System.out.println(queue2);
    }

    static void after() {
        Comparator comparator = (o1, o2) -> 1;
        Queue<Integer> queue = new PriorityQueue<>(comparator);
        queue = Collections.checkedQueue(queue, Integer.class);
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue);

        Queue queue2 = queue;
        queue2.offer("three");
        System.out.println(queue2);
    }

    public static void main(String[] args) {
        before();
        after();
        /*
        Queueに詰める際に型を強制することができ、クライアント側で無駄な例外処理を省くことができる。
        クライアント側での予期せぬエラーを防げる。
         */
    }
}
