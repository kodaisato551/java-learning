package ch04.ex02;

import java.util.Comparator;
import java.util.List;

/**
 * どんなオブジェクトもSort可能なSortHarnessを書く.
 * コンパレータ―を受けとりそれに基づいてソートする
 * @author sato kodai
 */
public class SortHarness<T> implements Sort {

	private List<T> values;
	private Comparator<T> comparator;

	public SortHarness(List<T> data, Comparator<T> comparator) {
		values = data;
		this.comparator = comparator;
	}

	public void sort() {
		values.sort(comparator);
	}

	public List<T> getList() {
		return values;
	}

}
