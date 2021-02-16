package ch09.ex10;

import java.util.Objects;

public class LabeledPoint implements Comparable<LabeledPoint> {
    private String label;
    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabeledPoint that = (LabeledPoint) o;
        return x == that.x &&
                y == that.y &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, x, y);
    }

    @Override
    public int compareTo(LabeledPoint o) {
        if (o.label.equals(label)) {
            return 0;
        }
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
