package ch08.ex01;

public class UnsignedInt {
    private final int num;

    public UnsignedInt(int num) {
        this.num = num;
    }

    public long add(int other) {
        return Integer.toUnsignedLong(num) + Integer.toUnsignedLong(other);
    }

    public long minus(int other) {
        return Integer.toUnsignedLong(num) - Integer.toUnsignedLong(other);
    }

    public int divide(int other) {
        return Integer.divideUnsigned(num, other);
    }

    public int compare(int other) {
        return Integer.compare(num, other);
    }
}
