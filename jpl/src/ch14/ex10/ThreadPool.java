/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

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
	private final PoolWorker[] threads;
	private final int queueSize;
	private final int numberOfThreads;
	private final List<Runnable> taskQueue;

	private boolean isStarting = false;
	private boolean isCallStart = false;

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

		ThreadPoolExecutor tPoolExecutor;

	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		System.out.println("THREAD POOL ::start() call");
		isStarting = true;
		isCallStart = true;
		for (int i = 0; i < numberOfThreads; i++) {
			if (threads[i].getState().equals(State.NEW)) {
				threads[i].shutdownFlg = false;
				threads[i].start();
			} else {
				throw new IllegalStateException();
			}
		}

		System.out.println("THREAD POOL:: start() done");
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
		waitAllTaskComplete();
		//すでにstopがコール済みの場合は例外を吐く
		if (!isStarting) {
			throw new IllegalStateException();
		}
		System.out.println("stop called taskQuesize : " + taskQueue.size());
		isStarting = false;
		for (PoolWorker thread : threads) {
			thread.stopThread();
		}
		System.out.println("THREAD POOL : stop done");
	}

	private synchronized void waitAllTaskComplete() {
		while (taskQueue.size() != 0) {
			try {
				System.out.println(
						"waiting all tasks compl" + Thread.currentThread().getName() + " quesize : "
								+ taskQueue.size());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void stopBase() {
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		System.out.println("dispatch call isStarting flag = " + isStarting);
		if (!isCallStart) {
			throw new IllegalStateException();
		}
		/*
		 * start されてない状態
		 *・start()が呼ばれていない場合
		 *・stop()がコールされたとき
		 */
		System.out.println("taskQueue size : [" + taskQueue.size() + "] queueSize : [" + queueSize + "]");
		if (isStarting) {
			while (taskQueue.size() >= queueSize) {
				try {
					System.out.println("dispatch waiting " + Thread.currentThread().getName());
					wait();
				} catch (InterruptedException e) {
				}
			}
			taskQueue.add(runnable);
			notifyAll();
		}

		System.out.println("THREAD POOL : dispatch done");
	}

	/**
	 * take task from taskQueue
	 * @return
	 */
	public synchronized Runnable takeTask() {
		while (this.taskQueue.size() <= 0) {
			if (!isStarting) {
				return null;
			}
			try {
				System.out.println("taking task waiting " + Thread.currentThread().getName());
				wait();
			} catch (InterruptedException e) {
			}
		}
		Runnable runnable = taskQueue.remove(0);
		notifyAll();
		return runnable;
	}

	private class PoolWorker extends Thread {

		private volatile boolean shutdownFlg = true;

		public void stopThread() {
			this.shutdownFlg = true;
			try {
				join();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		private synchronized boolean getflg() {
			return shutdownFlg;
		}

		public void run() {
			Runnable r;
			while (!shutdownFlg) {
				r = takeTask();
				if (r == null) {
					break;
				}
				//execute
				try {
					System.out.println("running " + Thread.currentThread().getName());
					r.run();
				} catch (RuntimeException e) {
				} finally {

				}
			}
			System.out.println("thread state " + Thread.currentThread().getName() + " : "
					+ Thread.currentThread().getState().toString());
		}
	}
}