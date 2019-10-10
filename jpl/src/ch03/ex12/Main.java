package ch03.ex12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<String> list = Arrays.asList("Appldknvjdn", "Or", "Melon");
		Comparator<String> comparator = Comparator.comparing(String::length);
		SortHarness<String> sortHarness = new SortHarness<String>(list, comparator);

		System.out.println(list);
		sortHarness.sort();
		System.out.println(sortHarness.getList());

	}
}
