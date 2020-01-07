package ch14.ex05;

//14.4を修正してstaticの同期されたメソッドを使用しないでスレッドが安全に値を減算できるようにする。
//ちょっとわからなかったです
public class Value {
	private static int value;
	protected final Object lockObject = new Object();

	public Value(int integer) {
		this.value = integer;
	}

	public static synchronized void plus(int other) {
		value += other;
		System.out.println("thread name : " + Thread.currentThread().getName() + " value : " + value);
	}

	public int getValue() {
		return value;
	}

	public void minus(int other) {
		synchronized (lockObject) {
			value -= other;
			System.out.println("thread name : " + Thread.currentThread().getName() + " value : " + value);
		}

	}

	public static void main(String[] args) {
		Value value = new Value(1000);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					value.minus(1);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
	}

}
