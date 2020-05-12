package ch22.ex05;

/**
 * 出目
 */
public class Roll {
    private final int firstValue;
    private final int secondValue;
    private final int sum;

    public Roll(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        sum = firstValue + secondValue;
    }


    public int getSum() {
        return sum;
    }
}
