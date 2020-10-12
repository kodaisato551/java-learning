package practice;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDisp {

    private static final String filePath = "C:\\Users\\P000527216\\Downloads\\importList.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String str;
            List<String> lines = new ArrayList<>();
            Set<String> set = new HashSet<>();
            while ((str = reader.readLine()) != null) {
                if (!str.equals("\\n")) {
                    lines.add(str);
                    set.add(str);
                }
            }


            try (FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\P000527216\\Downloads\\classList.txt"));
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                 PrintWriter out = new PrintWriter(new BufferedWriter(outputStreamWriter))) {


                //ラムダ式でファイルへリストの内容を出力する
                set.forEach(s -> {
                    out.println(s);
//                    out.println("\n");
                });
            } catch (IOException e) {
                throw e;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
