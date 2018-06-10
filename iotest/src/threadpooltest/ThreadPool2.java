package threadpooltest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 
 * ���ܸ�Ҫ�������̳߳�ʵ��-submit���� �΄� 
 *    
 */ 
class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	/**
	 * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String call() throws Exception {
		System.out.println("call()�������Զ�����,�ɻ����             "
				+ Thread.currentThread().getName());
		// һ��ģ���ʱ�Ĳ���
		for (int i = 999999; i > 0; i--)
			;
		return "call()�������Զ����ã�����Ľ���ǣ�" + id + "    "
				+ Thread.currentThread().getName();
	}
} 
public class ThreadPool2 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>(); // �Y�����ؔ��M
																			// Future����

		// ����10������ִ��
		for (int i = 0; i < 10; i++) {
			// ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������
			Future<String> future = executorService
					.submit(new TaskWithResult(i));
			// submit�����ջ��ǵ��� ��execute����
			// ������ִ�н���洢��List��
			resultList.add(future);
		}
		// ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�����������������Ѿ��رգ������û���������á�
		executorService.shutdown();
		/*
		 * shutdown() ͨ������execute���档�������
		 * �����������һ���棬������ǰ�̳߳��Ѳ��ٽ��������ӵ��̣߳������ӵ��̻߳ᱻ�ܾ�ִ��
		 * ����һ���棬�����������߳�ִ�����ʱ�������̳߳ص���Դ��ע�⣬���������Ϲر��̳߳أ�
		 */
		// ��������Ľ��
		for (Future<String> fs : resultList) {
			try {
				// ÿ��get�� �����˵�
				System.out.println(fs.get() + "+main"); // ��ӡ�����̣߳�����ִ�еĽ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {

			}
		}
	}
}