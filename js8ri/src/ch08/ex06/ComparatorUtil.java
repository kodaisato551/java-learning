package ch08.ex06;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

/**
 * Comparatorインタフェースのメソッドだけを使用して、Point2Dに対する全順序（totalordering）なコンパレータを定義しなさい
 * （すなわち、同値のオブジェクトに対してのみ0を返すコンパレータです）。
 * ヒント：最初にx座標を比較し、その後に、y座標を比較します。同じことをRectangle2Dに対して行いなさい。
 */
public class ComparatorUtil {
    static final Comparator<Point2D> point2DComparator = Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);
    static final Comparator<Rectangle2D> rectangle2DComparator = Comparator.comparing(Rectangle2D::getX).thenComparing(Rectangle2D::getY);

    public static void main(String[] args) {
        int c = point2DComparator.compare(new Point(0, 1), new Point(2, 0));
        System.out.println(c);
    }
}
