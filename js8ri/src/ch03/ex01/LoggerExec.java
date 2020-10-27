package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 条件 的 な ロギング を 提供 する こと で、 遅延 ロギング 技法 を 強化 し なさい。 典型的 な 呼び出し は、
 * logIf( Level. FINEST, () -> i == 10, () -> "a[ 10] = " + a[ 10]) となり ます。
 * ロガー が メッセージ を ロギング し ない ので あれ ば、 その 条件 を 評価 しない よう に し なさい。
 */
public class LoggerExec {
    private final Logger logger;

    public LoggerExec(Logger logger) {
        this.logger = logger;
    }

    /**
     * @param level
     */
    public void logIf(Level level, BooleanSupplier condition, Supplier<String> msg) {
        if (condition.getAsBoolean()) {
            logger.log(level, msg.get());
        }
    }

    public static void main(String[] args) {
        LoggerExec loggerExec = new LoggerExec(Logger.getLogger("hoge"));
        loggerExec.logIf(Level.INFO, () -> true, () -> "message");
    }
}
