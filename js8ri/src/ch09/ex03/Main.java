package ch09.ex03;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

/**
 * 複数 例外 を キャッチ する catch 節 で キャッチ し た 例外 を 再度 スロー する 場合 に、
 * その 処理 が 書か れ て いる メソッド の throws 宣言 には、 例外 の 型 を どの よう に 宣言 し ます か。
 */
public class Main {

    public void process() throws FileNotFoundException, UnknownHostException {
        try {
            throwFileNotFoundException();
            throwUnknownHostException();
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }

    private void throwFileNotFoundException() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    private void throwUnknownHostException() throws UnknownHostException {
        throw new UnknownHostException();
    }
}
