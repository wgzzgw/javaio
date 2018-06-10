package threadtest;
//LocalThread
public class ThreadLocalTest {
	private static final ThreadLocal<Integer> local = ThreadLocal
			.withInitial(() -> 0);

	static class MyThread extends Thread {
		private int end;

		public MyThread(int end) {
			this.end = end;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()
					+ " start, local = " + local.get());
			for (int i = 0; i <= end; i++) {
				local.set(local.get() + i); // ¼ÆËã(end+1)*end/2µÄÖµ
			}
			System.out.println(Thread.currentThread().getName()
					+ " end, local = " + local.get());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i += 3) {
			new MyThread(i).start();
		}
	}
}
