
package ch16.ex01;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {
	public static void main(String[] args) {
		TypeDesc dect = new TypeDesc();
		for (String name : args) {
			try {
				Class<?> startClass = Class.forName(name);
				dect.printType(startClass, 0, basic);
			} catch (ClassNotFoundException e) {
				System.err.println(e);//report the errer
			}
		}
	}

	// デフォルトで標準出力に表示する
	private java.io.PrintStream out = System.out;

	// 型名にラベル付けするprintType()で使用される
	private static String[] basic = { "class", "interface", "enum", "annotation" },
			supercl = { "extends", "implemants" }, iFace = { null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		if (type == null || type == Object.class) {// 再帰呼び出し停止：スーパータイプが存在しない
			return;
		}
		// TypeをClassオブジェクトに変換する
		Class<?> cls = null;
		if (type instanceof Class<?>) {
			cls = (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		} else {
			throw new Error("Unexpected non-class type");
		}
		// この型を表示
		for (int i = 0; i < depth; i++) {
			out.print(" ");
		}
		int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2 : cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		// あればジェネリック型パラメータを表示
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		} else {
			out.println();
		}
		// このクラスが実装しているすべてのインタフェースを表示
		Type[] interfaces = cls.getGenericInterfaces();
		for (Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}
		// スーパークラスに対して再帰
		printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}
}