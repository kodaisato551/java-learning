/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.lang.Thread.State;
import java.util.ArrayList;

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
	private final ArrayList<Runnable> taskQueue;

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
		taskQueue = new ArrayList<>(queueSize);
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
		for (int i = 0; i < numberOfThreads; i++) {
			if (threads[i].getState().equals(State.NEW)) {
				throw new IllegalStateException();
			} else {
				threads[i].stopThread();
				PoolWorker tmp = threads[i];
				threads[i] = null;
				tmp.interrupt();

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
	public void dispatch(Runnable runnable) {
		synchronized (taskQueue) {
			taskQueue.add(runnable);
			taskQueue.notify();
		}
	}

	private class PoolWorker extends Thread {

		private boolean isActive = true;

		public void stopThread() {
			this.isActive = false;
		}

		//		public void run() {
		//			Thread thisThread = Thread.currentThread();
		//			Runnable r;
		//			while (this == thisThread) {
		//				synchronized (taskQueue) {
		//					while (taskQueue.isEmpty()) {
		//						try {
		//							taskQueue.wait();
		//						} catch (InterruptedException e) {
		//							Thread.currentThread().interrupt();
		//						}
		//					}
		//					r = (Runnable) taskQueue.remove(0);
		//				}
		//				try {
		//					r.run();
		//				} catch (Exception e) {
		//				}
		//			}
		//		}

		public void run() {

			Runnable r;
			while (isActive) {
				synchronized (taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							taskQueue.wait();
						} catch (InterruptedException ignored) {
							Thread.currentThread().interrupt();
						}
					}
					r = (Runnable) taskQueue.remove(0);
				}
				// If we don't catch RuntimeException, // the pool could leak threads
				try {
					r.run();
				} catch (RuntimeException e) {
					// You might want to log something here
				}
			}
		}
	}
}