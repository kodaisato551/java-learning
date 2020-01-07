package ch14.ex06;

public class SyncObject {
	public synchronized void waitMethod() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void notifyAllMethod() {
		notifyAll();
	}
}
