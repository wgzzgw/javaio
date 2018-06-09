package threadtest;
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicIdGenerator {
	private final static AtomicInteger counter = new AtomicInteger(0);  
	   public static int getNext(){  
	      return counter.getAndIncrement(); //���� 
	   }  
	  /* // getAndIncrement�������ڲ�ʵ�ַ�ʽ����Ҳ��CAS������һ��ģʽ��CAS������һ���ɹ������԰�װ��һ������ѭ���У�ֱ���ɹ�  
	   public final int getAndIncrement(){  
		   for(;;){  
		      int current = get();  
		      int next = current +1;  
		      if(compareAndSet(current,next))  
		         return current;  
		   }  
		}  */
	   public static void main(String[] args) throws InterruptedException   {  
		   for(int i=0;i<10;i++){
	    	new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	getNext();  
	            }  
	        }).start(); 
		   }
	        Thread.sleep(1000);
	        System.out.println("���н��:Counter.count=" + counter);  
	    }  
}