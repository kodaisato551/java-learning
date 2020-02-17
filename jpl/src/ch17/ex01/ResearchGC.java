package ch17.ex01;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO なんか違う気がする
 */
public class ResearchGC {
    private static final int maxIter = 1000000;
    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        ResearchGC.showAvailableMemory("before creation obj");
        for (int i = 0; i < ResearchGC.maxIter; i++) {
            ResearchGC.list.add(new Object());
            ResearchGC.showAvailableMemory("create obj " + (i + 1));
        }
        ResearchGC.showAvailableMemory("after creation");
        ResearchGC.releaseObjects();
        ResearchGC.showAvailableMemory("free of obj");
        Runtime.getRuntime().gc();
        ResearchGC.showAvailableMemory("after gc");
    }

    private static void showAvailableMemory(String messageStat) {
        System.out.println("[" + messageStat + "] " + "Available Memory : " + Runtime.getRuntime().freeMemory());
    }

    private static void releaseObjects() {
        if (ResearchGC.list == null) {
            return;
        }
        ResearchGC.list.clear();
        ResearchGC.list = null;
    }

}
