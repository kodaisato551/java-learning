package ch14.ex03;

public class Value {
	private int value;

	public Value(int integer) {
		this.value = integer;
	}

	public synchronized void plus(int other) {
		this.value += other;
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
					value.plus(1);
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
