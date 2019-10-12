package ch05.ex02;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();

		list.add("a");
		list.add("b");
		list.add("c");

		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));

		System.out.println(list.isEmpty());
		System.out.println(list.remove(0));
	}
}
