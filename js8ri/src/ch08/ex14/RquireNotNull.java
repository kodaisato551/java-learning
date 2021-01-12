package ch08.ex14;

import java.util.Objects;
import java.util.function.Supplier;

public class RquireNotNull {
    public static void print(String str) {
        Objects.requireNonNull(str, createNullMessageWithInvoker("str"));
        System.out.println(str);
    }

    public static void main(String[] args) {
        print("non-null");
        try {
            print(null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Supplier<String> createNullMessageWithInvoker(String paramName) {
        return () -> {
            return paramName + " is null";
        };
    }
}
