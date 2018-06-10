package threadtest;

import java.util.Random;

public class LocalThreadTest2 {
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

	/**
	 * 获取数据
	 * 
	 */
	static class A {
		public void get() {
			int data = x.get();
			System.out.println("A from " + Thread.currentThread().getName()
					+ " get data :" + data);

		}
	}

	/**
	 * 获取数据
	 * 
	 */
	static class B {
		public void get() {
			int data = x.get();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get data :" + data);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data :" + data);
					x.set(data);

					new A().get();
					new B().get();
				}
			}).start();
		}
	}
}
