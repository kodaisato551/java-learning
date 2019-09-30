package ch03.ex05;

public class ExtendedBenchmark extends Benchmark {

	private final int loopCounter;

	public ExtendedBenchmark(int loopCounter) {
		this.loopCounter = loopCounter;
	}

	@Override
	void benchmark() {
		for (int i = 0; i < loopCounter; i++) {
		}
	}

	/**
	 * benchmarkを実行し、その時間[ナノ秒]で返します
	 * @return
	 */
	public long measurement() {
		long start = System.nanoTime();
		benchmark();
		return (System.nanoTime() - start);
	}

}
