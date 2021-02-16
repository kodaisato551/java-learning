package ch09.ex08;

/**
 * 9. 3. 3 項 の「 数値 型 を 比較 する」 の Point クラス の compareTo メソッド を、 Integer. compareTo を 使用 せ ず に 実装 し なさい。
 */
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (x > o.x) {
            return 1;
        } else if (x < o.x) {
            return -1;
        } else if (y > o.y) {
            return 1;
        } else if (y < o.y) {
            return -1;
        } else {
            return 0;
        }
    }
}
