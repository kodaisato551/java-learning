package ch08.ex03;

/**
 * ユークリッドのアルゴリズム（200年以上前のものです）は、
 * 2つの数値の最大公約数（greatestcommondivisor）を計算します。
 * bが0であれば、gcd(a,b)=aであり、
 * そうでなければ、gcd(b,rem(a,b))です。remは、余りを意味しています。aかbが負であったとしても、
 * 明らかにgcdは、負になるべきではありません
 * （なぜなら、その値の符号を正にしたものの方がより大きな約数となるからです）。
 * gcdのアルゴリズムを%、floorMode、数学的な（負ではない）余りを生成するrem関数で実装しなさい。
 * これらの3つの方法のどれが負の値に対して最も簡単ですか。
 */
public class Test {
    static int gcd(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        } else {
            return gcd(b, a % b);
        }
    }

    static int gcd2(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        } else {
            return gcd2(b, Math.floorMod(a, b));
        }
    }

    static int gcd3(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        } else if (b == 0) {
            return Math.abs(a);
        } else {
            return gcd3(b, rem(a, b));
        }
    }

    static int rem(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("cannot divide with 0");
        }
        if (a > 0) {
            return a % b;
        } else if (b > 0) {
            return Math.floorMod(a, b);
        } else {
            return a - b * (a / b - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(12, 63));
        System.out.println(gcd(-15, 25));
        System.out.println(gcd(12, 12));
        System.out.println(gcd(-15, -25));
    }
}
