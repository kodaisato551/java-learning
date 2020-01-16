package ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMenbers(c, c.getFields());
			printMenbers(c.getDeclaredFields());
			printMenbers(c, c.getConstructors());
			printMenbers(c.getDeclaredConstructors());
			printMenbers(c, c.getMethods());
			printMenbers(c.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	public static void printMenbers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			System.out.println(m.toString());
			if (m instanceof AnnotatedElement) {
				printAnnotation((AnnotatedElement) m);
			}

		}
	}

	public static void printMenbers(Class<?> c, Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			} else if (m.getDeclaringClass() == c) {
				continue;
			}
			System.out.println(m.toString());
		}
	}

	public static void printAnnotation(AnnotatedElement mems) {
		Annotation[] annotation = mems.getAnnotations();
		for (Annotation a : annotation) {
			System.out.println(a.toString());
		}
	}
}
