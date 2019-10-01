package ch03.ex05;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODOテスト合格の判定基準があまりにも決め打ちすぎる何か良い方法はないか
 * @author Sato Kodai
 *
 */
public class BenchmarkTest {
	private static ExtendedBenchmark target;
	private static final double EPS = 10.0;

	@BeforeClass
	public static void setup() {
		target = new ExtendedBenchmark(10000);
	}

	@Test
	public void testLoopBenchmark() {
		long start = System.nanoTime();
		long actual = target.measurement();
		long expected = System.nanoTime() - start;
		double diff = Math.abs(expected - actual) / 1000.0;
		System.out.println(diff);

		assertTrue(diff < EPS);

	}
}
