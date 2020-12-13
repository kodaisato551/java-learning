package ch06.ex03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 1,000個のスレッドを生成し、
 * 各スレッドは、ある1つのカウンターを100,000回だけ1ずつ増加させます。
 * AtomicLongとLongAdderを使用した場合の性能を比較しなさい。
 */
public class SpeedTest {

    /**
     * スレッド数と各スレッドで実行するタスクを指定して、処理速度を返します（ミリ秒）
     *
     * @param threadNum
     * @param task
     * @return
     */
    public static long measure(int threadNum, Runnable task) {
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        long start = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            service.submit(task);
        }
        service.shutdown();
        try {
            service.awaitTermination(15, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();

        return end - start;
    }

    public static void main(String[] args) {

        long timeWithAtomicLong = measure(1000, () -> {
            AtomicLong atomicLong = new AtomicLong();
            for (int i = 0; i < 100000; i++) {
                atomicLong.incrementAndGet();
            }
        });

        long timeWithLongAdder = measure(1000, () -> {
            LongAdder adder = new LongAdder();
            for (int i = 0; i < 100000; i++) {
                adder.increment();
            }
        });

        System.out.println("AtomicLong : " + timeWithAtomicLong + "[ms]");
        System.out.println("LongAdder  : " + timeWithLongAdder + "[ms]");

    }

}
