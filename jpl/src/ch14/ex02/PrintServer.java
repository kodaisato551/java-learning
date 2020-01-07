package ch14.ex02;

/*
 * スレッドの識別により、コンストラクタで生成されたスレッドだけがrunを実行できるようにする
 */
public class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	private final long threadID;

	public PrintServer() {
		Thread printThread = new Thread(this);
		threadID = printThread.getId();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (Thread.currentThread().getId() == threadID) {
			for (;;) {
				realPrint(requests.remove());
			}
		} else {
			throw new IllegalStateException();
		}
	}

	private void realPrint(PrintJob job) {
		/*
		 * 実際のPrintの処理を書く
		 */
	}
}
