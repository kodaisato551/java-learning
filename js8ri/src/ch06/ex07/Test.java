package ch06.ex07;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap < String, Long > 内 で、
 * 最大値 を 持つ キー を 見つけ なさい（ 同じ 最大値 を 持つ キー が あれ ば、 どちら の キー でも かまい ませ ん）。 ヒント： reduceEntries。
 */
public class Test {
    public static String findMaxkey(ConcurrentHashMap<String, Long> map, int threshhold) {
        return map.reduceEntries(threshhold,
                (e1, e2) -> e1.getValue().compareTo(e2.getValue()) > 0 ? e1 : e2).getKey();
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("a", 1L);
        map.put("b", 100L);
        map.put("c", 3L);
        String str = findMaxkey(map, 5);
        System.out.println(str);
    }
}
