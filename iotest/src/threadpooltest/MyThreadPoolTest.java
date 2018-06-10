package threadpooltest;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//自定义线程池
public class MyThreadPoolTest {
	private static int produceTaskSleepTime = 2;//生成任务所需模拟时间
	private static int produceTaskMaxNumber = 10;//总共10个任务

	public static void main(String[] args) {
		// 构造一个线程池
		//核心线程数2 最大线程数4 缓冲区3 
		//参数7：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
		//空闲线程在3秒内没有接收到新任务时 回收
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardOldestPolicy());

		for (int i = 1; i <= produceTaskMaxNumber; i++) {
			try {
				// 产生一个任务，并将其加入到线程池
				String task = "task@ " + i;
				System.out.println("put " + task);
				threadPool.execute(new ThreadPoolTask(task));
				// 便于观察，等待一段时间
				Thread.sleep(produceTaskSleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 线程池执行的任务
 */
class ThreadPoolTask implements Runnable, Serializable {
	private static final long serialVersionUID = 0;
	private static int consumeTaskSleepTime = 2000;
	// 保存任务所需要的数据
	private Object threadPoolTaskData;

	ThreadPoolTask(Object tasks) {
		this.threadPoolTaskData = tasks;
	}

	public void run() {
		// 处理一个任务，这里的处理方式仅仅是一个打印语句
		System.out.println(Thread.currentThread().getName());
		System.out.println("start .." + threadPoolTaskData);

		try {
			// //便于观察，等待一段时间
			Thread.sleep(consumeTaskSleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadPoolTaskData = null;
	}

	public Object getTask() {
		return this.threadPoolTaskData;
	}
}
