package ch07;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * exec js on jvm
 */
public class Util {

    public static void nashornExec(String path) throws ScriptException {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
//                System.out.println(data);
                sb.append(data);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("nashorn");
        se.eval(sb.toString());
    }
}
