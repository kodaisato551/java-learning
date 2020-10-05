package ch01.ex07;

/**
 *
 */
public class RunnableUtil {
    private static Runnable andThen(Runnable run1, Runnable run2) {
        return () -> {
            run1.run();
            run2.run();
        };
    }

    public static void main(String[] args) {
        new Thread(
                RunnableUtil.andThen(() -> System.out.println("first"), () -> System.out.println("second"))
        ).start();
    }

}
