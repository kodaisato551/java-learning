package ch07.ex04;

import ch07.Util;

import javax.script.ScriptException;

/**
 * 文字列から部分文字列を抽出することでリテラルでないJavaScript文字列を生成し、getClassメソッドを呼び出しなさい。結果はどのクラスになりますか。
 * それから、そのクラスをjava.lang.String.class.castへ渡しなさい。
 * 何が起きますか。それが起きた理由は何ですか。
 */
public class Main {
    public static void main(String[] args) throws ScriptException {
        Util.nashornExec("/Users/satokodai/Study/java-learning/js8ri/src/ch07/ex04/source.js");
    }
}
