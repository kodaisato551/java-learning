package ch01.ex06;

/**
 * チェックされるすべての例外をキャッチしチェックされない
 * 例外に変えるuncheckメソッドを書く
 */
interface RunnableEx {
    //run
    void run() throws Exception;

    //ラムダを使用します
    static Runnable uncheck(RunnableEx runner) {
        return () -> {
            try {
                runner.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
