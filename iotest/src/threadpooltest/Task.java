package threadpooltest;

import java.util.Date;

/**
 * 功能概要：缓冲线程池实例-execute运行 也就是任务 放在线程执行的
 */
public class Task implements Runnable {
	private String name; // 任務名字

	public Task(String name) {
		this.name = "thread" + name;
	}

	@Override
	public void run() {
		System.out.println(name + " Start. Time = " + new Date());
		processCommand();
		System.out.println(name + " End. Time = " + new Date());

	}

	private void processCommand() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
