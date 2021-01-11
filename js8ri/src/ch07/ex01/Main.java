package ch07.ex01;

import ch07.Util;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * たとえば、ZonedDateTimeクラスなど、試してみたいJavaAPIを選びなさい。
 * そのうえで、オブジェクトの生成、メソッドの呼び出し、
 * 戻り値の表示など、jjsを使った実験を行いなさい。
 * また、Javaでテストプログラムを書くよりは簡単かどうかを検証しなさい。
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ScriptException {
//        ScriptEngineManager manager = new ScriptEngineManager(null);
//        ScriptEngine engine = manager.getEngineByName("nashorn");
//
////        Path path = Paths.get(JS_FILE_PATH);
//        engine.eval("var time = java.time.LocalDateTime;"
//                + "var now = time.now();"
//                + "print(now)"
//        );

        Util.nashornExec("/Users/satokodai/Study/java-learning/js8ri/src/ch07/ex01/test.js");

    }
}
