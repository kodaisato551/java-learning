package ch03.ex09;

import java.util.Comparator;

/**
 * 指定 さ れ た 順序 で、 指定 さ れ た フィールド を 比較 する コンパレータ を 生成 する lexicographicComparator( String... fieldNames) メソッド を 書き なさい。
 * たとえば、 lexicographicComparator(" lastname", "firstname") は、 2 つ の オブジェクト を 受け取り、 リフレクション を 使用 し て、 lastname フィールド の 値 を 取得 し ます。
 * 2 つ の オブジェクト の lastname フィールド が 異なれ ば、 その 差 を 返し ます。 同じ で あれ ば、 firstname フィールド に 移り ます。 すべて の フィールド が 同じ で あれ ば、 0 を 返し ます。
 * <p>
 * TODO 難しい
 */
public class Main {
    public static Comparator<Object> lexicographicComparator(String... fieldNames) {
        Comparator<Object> comparator = Comparator.comparing(o -> getFieldSigniture(o, fieldNames[0]));
        for (int i = 1; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];
            comparator = comparator.thenComparing(Comparator.comparing(o -> getFieldSigniture(o, fieldName)));
        }
        return comparator;
    }

    private static String getFieldSigniture(Object obj, String fieldName) {
        try {
            return obj.getClass().getDeclaredField(fieldName).get(obj).toString();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

}
