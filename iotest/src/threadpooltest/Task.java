package threadpooltest;

import java.util.Date;

/**
 * ���ܸ�Ҫ�������̳߳�ʵ��-execute���� Ҳ�������� �����߳�ִ�е�
 */
public class Task implements Runnable {
	private String name; // �΄�����

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
