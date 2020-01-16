/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.<br><br>
 *
 * [Instruction]
 * <ul>
 *  <li> Implement one constructor and three methods. </li>
 *  <li> Don't forget to write a Test program to test this class. </li>
 *  <li> Pay attention to @throws tags in the javadoc. </li>
 *  <li> If needed, you can put "synchronized" keyword to methods. </li>
 *  <li> All classes for implementation must be private inside this class. </li>
 *  <li> Don't use java.util.concurrent package. </li>
 *  <li> Don't use {@link java.lang.Thread#interrupt}  method to stop a thread</li>
 *  </ul>
 *
 *  @author Yoshiki Shibata
 */
public class ThreadPool {
	private PoolWorker[] threads;
	private final int queueSize;
	private final int numberOfThreads;
	private final List<Runnable> taskQueue;

	private boolean isStopped = false;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1) {
			throw new IllegalArgumentException();
		}
		this.queueSize = queueSize;
		this.numberOfThreads = numberOfThreads;
		taskQueue = new ArrayList<>();
		threads = new PoolWorker[numberOfThreads];
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new PoolWorker();
		}

	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		for (int i = 0; i < numberOfThreads; i++) {
			if (threads[i].getState().equals(State.NEW)) {
				threads[i].start();
			} else {
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
	 * すべてのスレッドを正常に停止し、その終了を待ちます。
	 * このメソッドが呼び出される前にディスパッチされたすべてのリクエストは完了する必要があり、
	 * このメソッドもその完了を待ちます。
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public void stop() {
		isStopped = true;
		for (int i = 0; i < numberOfThreads; i++) {
			if (threads[i].getState().equals(State.NEW)) {
				throw new IllegalStateException();
			} else {
				threads[i].stopThread();
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		//waitAllTaskComplete();
	}

	/**
	 * wait till all task done
	 */
	private void waitAllTaskComplete() {
		boolean isAllThreadsTerminated = true;

		while (true) {
			for (int i = 0; i < numberOfThreads; i++) {
				if (!threads[i].getState().equals(State.TERMINATED)) {
					System.out.println(threads[i].getName());
					isAllThreadsTerminated = false;
					break;
				}
			}
			if (isAllThreadsTerminated) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}

		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 * プール内のスレッドを使用して、指定されたRunnableオブジェクトを実行します。
	 * run（）メソッドがスレッドで呼び出されます。
	 * キューがいっぱいの場合、このメソッド呼び出しは、キューがいっぱいになるまでブロックされます。
	 *
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		Objects.requireNonNull(runnable);
		if (!isStopped) {
			while (queueSize >= taskQueue.size()) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			taskQueue.add(runnable);
			notifyAll();

		}
	}

	/**
	 * take task from taskQueue
	 * @return
	 */
	public synchronized Runnable takeTask() {
		while (this.taskQueue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		Runnable runnable = taskQueue.remove(0);
		notifyAll();
		return runnable;
	}

	private class PoolWorker extends Thread {

		private volatile boolean shutdownFlg = false;

		public void stopThread() {
			this.shutdownFlg = true;
			interrupt();
		}

		public void run() {
			Runnable r;
			while (!shutdownFlg) {
				r = takeTask();
				//execute
				try {
					r.run();
				} catch (RuntimeException e) {
				}
			}
		}
	}
}