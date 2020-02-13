package interpret.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JTextField;

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

	private static List<Class<?>> convertClassObjFromString(List<String> paramNames) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		for (String str : paramNames) {
			if (LexicalAnalyzer.primitiveConverter.get(str) != null) {
				classes.add(LexicalAnalyzer.primitiveConverter.get(str));
			} else if (LexicalAnalyzer.primitiveArrayConverter.get(str) != null) {
				classes.add(LexicalAnalyzer.primitiveArrayConverter.get(str));
			} else {
				classes.add(Class.forName(str));
			}
		}
		return classes;
	}

	/**
	 * String　で受け取ったクラス情報と実態をもとに適切なオブジェクトに変換する
	 *
	 * @param paramClassNameList
	 * @param inputs
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
			LexicalAnalyzer.parseAndInsertToMap(classes.get(i), strings.get(i), objs);
		}
		return objs.toArray();
	}

	private static void parseAndInsertToMap(Class<?> clazz, String value, List<Object> objs)
			throws NumberFormatException {

		if (clazz.isArray()) {
			String[] tmp = value.split(",");
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
			}
		}

	}

}
