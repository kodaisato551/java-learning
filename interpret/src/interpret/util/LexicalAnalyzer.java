package interpret.util;

import interpret.data.ObjectPool;

import javax.swing.*;
import java.lang.reflect.Executable;
import java.util.*;

/**
 * define util method here
 * 面倒な処理は全部ここ
 */
public class LexicalAnalyzer {

	private static final Map<String, Class<?>> primitiveConverter = new HashMap<String, Class<?>>() {
		private static final long serialVersionUID = 1390406511496673145L;

		{
			put("int", int.class);
			put("long", long.class);
			put("short", short.class);
			put("byte", byte.class);
			put("double", double.class);
			put("float", float.class);
			put("boolean", boolean.class);
			put("char", char.class);
		}

	};

	private static final Map<String, Class<?>> primitiveArrayConverter = new HashMap<String, Class<?>>() {
		private static final long serialVersionUID = -526503688944880084L;

		{
			put("int[]", int[].class);
			put("long[]", long[].class);
			put("short[]", short[].class);
			put("byte[]", byte[].class);
			put("double[]", double[].class);
			put("float[]", float[].class);
			put("boolean[]", boolean[].class);
			put("char[]", char[].class);

		}
	};


	/**
	 * メソッド・コンストラクタのSignitureから引数を抽出しStringのリストとして返す
	 * <p>
	 * 例：
	 * input>
	 * 　"public void method(String v1,int v2,double[] v3)"
	 * output>
	 * java.lang.String,int,[Ldouble
	 *
	 * @param signatureString
	 * @return
	 */
	public static List<String> findParams(String signatureString) {
		Objects.requireNonNull(signatureString);
		List<String> paramNames = new ArrayList<>(Arrays.asList(signatureString.split("[(,]", 0)));
		String[] tmp = paramNames.remove(paramNames.size() - 1).split("[)]", 0);
		if (tmp.length == 0) {
			return null;
		}
		paramNames.add(tmp[0]);
		paramNames.remove(0);// delete first elem prefix

		return paramNames;
	}

	/**
	 * @param methodOrConstructor Method,Constructorに応じて
	 * @return
	 */
	public static List<String> findParams(Executable methodOrConstructor) {
		List<String> paramNames = new ArrayList<>();
		for (Class<?> clazz : methodOrConstructor.getParameterTypes()) {
			paramNames.add(clazz.getName());
		}
		return paramNames;
	}

	/**
	 * パラメータの名前（String）を受け取りClass型のリストに変換する
	 *
	 * @param paramNames
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> convertClassObjFromString(List<String> paramNames)
			throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		for (String str : paramNames) {
			if (LexicalAnalyzer.primitiveConverter.get(str) != null) {
				classes.add(LexicalAnalyzer.primitiveConverter.get(str));
			} else if (LexicalAnalyzer.primitiveArrayConverter.get(str) != null) {
				classes.add(LexicalAnalyzer.primitiveArrayConverter.get(str));
			} else {
				try {
					classes.add(Class.forName(str));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new ClassNotFoundException();
				}
			}
		}
		return classes;
	}

	/**
	 * String　で受け取ったクラス情報と実態をもとに適切なオブジェクトに変換する
	 *
	 * @param paramClassNameList　コンストラクタ、メソッドの引数の型のStringのリスト
	 * @param inputs　各パラメータの入力JtextFieldのリスト
	 * @return
	 * @throws ClassNotFoundException,NumberFormatException
	 */
	public static Object[] parse(List<String> paramClassNameList, List<JTextField> inputs)
			throws ClassNotFoundException, NumberFormatException {
		if (paramClassNameList == null) {
			return null;
		}
		List<String> strings = new ArrayList<>();
		for (JTextField j : inputs) {
			strings.add(j.getText());
		}
		List<Class<?>> classes = LexicalAnalyzer.convertClassObjFromString(paramClassNameList);
		List<Object> objs = new ArrayList<>();
		for (int i = 0; i < paramClassNameList.size(); i++) {
			LexicalAnalyzer.createPrimitiveInstanceFromStringValue(classes.get(i), strings.get(i), objs);
		}
		return objs.toArray();
	}

	/**
	 * プリミティブ型のクラスの場合そのクラスのラッパーオブジェクトに変換する。
	 * Stringで受け取った入力とクラスの情報からそのクラスのオブジェクトを作成する。
	 * オブジェクトは生成されるリストとして返却される。
	 *
	 * @param clazz クラスの情報
	 * @param value 　そのクラスで
	 * @param objs  　生成されたオブジェクトのリスト。
	 * @throws NumberFormatException
	 */
	public static void createPrimitiveInstanceFromStringValue(Class<?> clazz, String value, List<Object> objs)
			throws NumberFormatException {

		if (clazz.isArray()) {
			String[] tmp = value.split(",");
			System.out.println(tmp);
			if (clazz == int[].class) {
				List<Integer> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(Integer.parseInt(str));
				}
				objs.add(list.toArray());
			} else if (clazz == short[].class) {
				List<Short> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(Short.parseShort(str));
				}
				objs.add(list.toArray());
			} else if (clazz == byte[].class) {
				List<Byte> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(Byte.parseByte(str));
				}
				objs.add(list.toArray());
			} else if (clazz == double[].class) {
				List<Double> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(Double.parseDouble(str));
				}
				objs.add(list.toArray());
			} else if (clazz == float[].class) {
				List<Float> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(Float.parseFloat(str));
				}
				objs.add(list.toArray());
			} else if (clazz == char[].class) {
				List<Character> list = new ArrayList<>();
				for (String str : tmp) {
					list.add(str.toCharArray()[0]);
				}
				objs.add(list.toArray());
			} else if (clazz == String[].class) {
				objs.add(tmp);
			} else {
				//ほかのOnjの配列
			}

		} else {
			if (clazz == int.class) {
				objs.add(Integer.parseInt(value));
			} else if (clazz == long.class) {
				objs.add(Long.parseLong(value));
			} else if (clazz == short.class) {
				objs.add(Short.parseShort(value));
			} else if (clazz == byte.class) {
				objs.add(Byte.parseByte(value));
			} else if (clazz == double.class) {
				objs.add(Double.parseDouble(value));
			} else if (clazz == float.class) {
				objs.add(Float.parseFloat(value));
			} else if (clazz == boolean.class) {
				objs.add(Boolean.parseBoolean(value));
			} else if (clazz == char.class) {
				objs.add(value.toCharArray()[0]);
			} else if (clazz == String.class) {
				objs.add(value);
			} else {
				// ほかのオブジェクト
				Object obj = ObjectPool.getInstance().getObjFromDisplayName(value);
				objs.add(obj);
			}
		}

	}

}
