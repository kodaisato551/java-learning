package ch08.ex02;

/**
 * Math.negateExact(n)が例外をスローすることになる整数nの値は何ですか
 * （ヒント：1つの値しか該当しません）。
 * <p>
 * <p>
 * オーバーフローは Integer.MIN VALUE が-2.147.483.648であることと、
 * 反対側の Integer.MAX VALUE が2.147.483.647であることが原因です。
 */
public class Test {
    public static void main(String[] args) {
        try {
            Math.negateExact(Integer.MIN_VALUE);
        } catch (ArithmeticException e) {
            System.out.println("Overflow");
        }
    }
}
