package ch14.ex02;

import java.util.ArrayList;
import java.util.List;

public class PrintQueue {
	private List<PrintJob> queue = new ArrayList<PrintJob>();

	public void add(PrintJob job) {
		queue.add(job);
	}

	/**
	 * Queueの先頭要素（最も古い要素）を取得し、その後該当の要素をキューから削除する
	 * @return
	 */
	public PrintJob remove() {
		return queue.remove(0);
	}
}
