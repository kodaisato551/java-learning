package ch14.ex06;

public class MassageDisplay implements Runnable {
	private final int interval;
	private int count = 0;
	private SyncObject syncObj = new SyncObject();
	private String message;

	public MassageDisplay(SyncObject syncObj, int interval, String message) {
		this.syncObj = syncObj;
		this.interval = interval;
		this.message = message;
	}

	@Override
	public void run() {
		while (true) {
			while (count < interval) {
				synchronized (syncObj) {
					syncObj.waitMethod();
					count++;
				}
			}
			System.out.println(message);
			count = 0;
		}
	}

}
