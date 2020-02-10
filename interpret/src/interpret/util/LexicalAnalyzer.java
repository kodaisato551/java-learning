package interpret.util;

import java.util.*;

/**
 * define util method here
 */
public class LexicalAnalyzer {

    private static final Map<String, Class<?>> primitiveConverter = new HashMap<>() {
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

    private static final Map<String, Class<?>> primitiveArrayConverter = new HashMap<>() {
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
     * receives the method's signature and returns a list of arguments
     *
     * @param method or constructor string
     * @return list of param name if no param return null.
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


    public static List<Class<?>> convertClassObjFromString(List<String> paramNames) throws ClassNotFoundException {
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


}
