package ch16.ex03;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(getCombinedArray(c.getFields(), c.getDeclaredFields()));
			printMembers(getCombinedArray(c.getConstructors(), c.getDeclaredConstructors()));
			printMembers(getCombinedArray(c.getMethods(), c.getDeclaredMethods()));
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang"));
		}
	}

	private static String strip(String src, String word) {
		return src.replaceAll(word + "\\.", "");
	}

	private static Member[] getCombinedArray(Member[]... memArrays) {
		Set<Member> memSet = new LinkedHashSet<>();
		for (Member[] mems : memArrays) {
			memSet.addAll(Arrays.asList(mems));
		}
		return memSet.toArray(new Member[memSet.size()]);
	}
}
