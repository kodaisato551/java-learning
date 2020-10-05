package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class Looper {
    public static void main(String[] args) {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        /* nameは、各イテレーションのものがキャプチャされる。 */
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }
        runners.forEach(Runnable::run);

        runners.clear();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println(name));
            /* iは実質的ファイナルでないため、キャプチャできない */
//            runners.add(() -> System.out.println(names[i]));
        }
        runners.forEach(Runnable::run);
    }
}
