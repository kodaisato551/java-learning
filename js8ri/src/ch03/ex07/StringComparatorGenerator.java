package ch03.ex07;

import java.util.Collections;
import java.util.Comparator;

/**
 * Comparator < String > を 生成 する メソッド を 書き なさい。 普通 の 順序、 逆順、 大文字 小文字 を 区別、 大文字 小文字 を 区別 し ない
 * 、 空白 を 含める、 空白 を 除外 する、 あるいは、 これらの組み合わせ と する こと が できる Comparator < String > に し なさい。
 * メソッド は、 ラムダ 式 を 返す よう に し なさい。
 * <p>
 * tODO テスト
 */
public class StringComparatorGenerator {
    /**
     * 自然な順序
     *
     * @return
     */
    public static Comparator<String> naturalOrder() {
        return Comparator.naturalOrder();
    }

    /**
     * 逆順
     */
    public static Comparator<String> reverseOrder() {
        return Collections.reverseOrder();
    }

    /**
     * 大文字小文字を区別しないコンパレータを生成。
     *
     * @param orgComparator
     * @return
     */
    public static Comparator<String> ignoreCapital(Comparator<String> orgComparator) {
        return (s1, s2) -> orgComparator.compare(s1.toLowerCase(), s2.toLowerCase());
    }

    /**
     * 大文字小文字を区別しないコンパレータを生成。
     *
     * @param orgComparator
     * @return
     */
    public static Comparator<String> ignoreBlank(Comparator<String> orgComparator) {
        return (s1, s2) -> orgComparator.compare(removeBlank(s1), removeBlank(s2));
    }

    /**
     * 渡された文字列に空行が含まれていたらそこを無視する文字列を生成。
     *
     * @return　文字列
     */
    private static String removeBlank(String string) {
//        return string.chars().mapToObj(c -> (char) c).filter(c -> c != ' ').map(String::valueOf).collect(Collectors.joining());
        return string.replaceAll("　", " ").replaceAll(" ", "");
    }


}
