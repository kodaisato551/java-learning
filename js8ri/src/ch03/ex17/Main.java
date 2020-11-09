package ch03.ex17;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Main {

    static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        AtomicBoolean handled = new AtomicBoolean();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    first.run();
                } catch (Throwable t) {
                    if (handled.compareAndSet(false, true)) {
                        handler.accept(t);
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    second.run();
                } catch (Throwable t) {
                    if (handled.compareAndSet(false, true)) {
                        handler.accept(t);
                    }
                }
            }

        };
        t1.start();
        t2.start();
    }
}
