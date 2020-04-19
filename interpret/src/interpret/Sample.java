package interpret;

import java.lang.reflect.Field;

public class Sample {
    public static void main(String[] args) {
        Object obj = "sato";
        Field[] fields = obj.getClass().getDeclaredFields();
        Field field = fields[0];
        String str =
        field.getType().getSimpleName();

        System.out.println(str);
    }
}
