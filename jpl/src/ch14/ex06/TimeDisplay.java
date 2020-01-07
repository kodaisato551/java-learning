package ch14.ex06;

public class TimeDisplay implements Runnable {
	private int time = 0;
	private MassageDisplay messageDisplay;
	private SyncObject syncObj = new SyncObject();

	public TimeDisplay(SyncObject syncObj, MassageDisplay messageDisplay) {
		this.syncObj = syncObj;
		this.messageDisplay = messageDisplay;
	}

	@Override
	public void run() {
		new Thread(messageDisplay).start();
		while (true) {
			try {
				Thread.sleep(1000);
				time++;
				System.out.println("time:" + time + "s");
				syncObj.notifyAllMethod();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void startMessageThread(MassageDisplay massage) {
		new Thread(massage).start();
	}
}
