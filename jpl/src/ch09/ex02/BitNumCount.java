package ch09.ex02;

/**
 * TODO M:test
 * @author p000527216
 *
 */
public class BitNumCount {
	/**
	 * 1でマスクする。
	 * マスク値１を左シフトしていき、＆をしたときに
	 * ０であれば：１が立ってない
	 * それ以外：１が立っている
	 * @param i
	 * @return
	 */
	public static int bitCount(int bits) {
		long startTime = System.nanoTime();
		int count = 0;
		for (int mask = 1; mask != 0; mask = mask << 1) {
			if ((mask & bits) != 0) {
				count++;
			}
		}
		long endTime = System.nanoTime();
		System.out.println("処理時間：" + (endTime - startTime) + " nano s");
		return count;
	}

	/**
	 * 最適化されているアルゴリズム
	 * @param bits
	 * @return
	 */
	public static int bitCount2(int bits) {
		long startTime = System.nanoTime();
		bits = (bits & 0x55555555) + (bits >> 1 & 0x55555555);
		bits = (bits & 0x33333333) + (bits >> 2 & 0x33333333);
		bits = (bits & 0x0f0f0f0f) + (bits >> 4 & 0x0f0f0f0f);
		bits = (bits & 0x00ff00ff) + (bits >> 8 & 0x00ff00ff);
		long endTime = System.nanoTime();
		System.out.println("処理時間：" + (endTime - startTime) + " nano s");
		return (bits & 0x0000ffff) + (bits >> 16 & 0x0000ffff);
	}

	public static void main(String[] args) {
		System.out.println(bitCount(12000));
		System.out.println(bitCount2(12000));
	}
}
