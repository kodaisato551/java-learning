package ch07.ex02;

import ch07.Util;

import javax.script.ScriptException;

/**
 * jjsを実行し、ストリームライブラリを使用して、次の問題に対する解法をインタラクティブに求めなさい。
 * 問題：あるファイルに含まれている長い単語（12文字より長い）を重複なしですべてソートして表示しなさい。
 * 最初に単語を読み込んで、長い単語を選択してという具合に行いなさい。
 * このインタラクティブな取り組み方は、通常のワークフローと比較してどうですか。
 */
public class Main {
    public static void main(String[] args) throws ScriptException {
        Util.nashornExec("/Users/satokodai/Study/java-learning/js8ri/src/ch07/ex02/source.js");
    }
}
