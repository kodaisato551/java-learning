package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ロックの獲得とロックの解放を行いつつ処理を行う
 */
public class WithLockExec {
    private final ReentrantLock myLock;

    public WithLockExec(ReentrantLock myLock) {
        this.myLock = myLock;
    }

    public void withLock(Runnable action) {
        myLock.lock();
        try {
            action.run();
        } finally {
            myLock.unlock();
        }
    }

    public static void main(String[] args) {

        WithLockExec withLockExec = new WithLockExec(new ReentrantLock());
        withLockExec.withLock(
                () -> System.out.println("Something Exec")
        );
    }
}
