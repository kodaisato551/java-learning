package interpret;

import java.lang.reflect.Method;

public class Sample {
    public static void main(String[] args) throws ClassNotFoundException {
//        Object obj = "sato";
//        Field[] fields = obj.getClass().getDeclaredFields();
//        Field field = fields[0];
//        String str =
//        field.getType().getSimpleName();
//
//        System.out.println(str);

        String str = String.class.getName();
        Class<?> clazz =
                Class.forName("interpret.Sample");

        Method[] methods = clazz.getMethods();
        Method mainFunc = methods[0];

        System.out.println(mainFunc.getParameterTypes()[0].getName());
    }
}
