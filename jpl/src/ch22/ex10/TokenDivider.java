package ch22.ex10;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * トークンに分割する
 */
public class TokenDivider {
    public static List<String> divide(String commentOutString, Readable source) {
        List<String> tokens = new ArrayList<>();
        Scanner in = new Scanner(source);
        String pattern = commentOutString + ".*";
        Pattern exp = Pattern.compile(pattern);

        while (in.hasNext()) {
            if (in.hasNext(exp)) {
                in.nextLine();
            } else {
                tokens.add(in.nextLine());
            }
        }
        return tokens;
    }


    public static void main(String[] args) {
        File file = new File("C:\\study\\java\\java-learning\\jpl\\src\\ch22\\ex10\\in.txt");
        try (Reader reader = new InputStreamReader(new FileInputStream(file))) {
            List<String> ret =
                    divide("#", reader);

            for (String s : ret) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
