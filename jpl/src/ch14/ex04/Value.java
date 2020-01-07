package ch14.ex04;

public class Value {
	private static int value;

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

	public static void main(String[] args) {
		Value value = new Value(0);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					Value.plus(1);
					try {
						Thread.sleep(50);
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
