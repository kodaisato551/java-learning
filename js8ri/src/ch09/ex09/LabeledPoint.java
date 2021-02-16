package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
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
}
