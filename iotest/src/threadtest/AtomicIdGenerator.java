package threadtest;
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicIdGenerator {
	private final static AtomicInteger counter = new AtomicInteger(0);  
	   public static int getNext(){  
	      return counter.getAndIncrement(); //自增 
	   }  
	  /* // getAndIncrement方法的内部实现方式，这也是CAS方法的一般模式，CAS方法不一定成功，所以包装在一个无限循环中，直到成功  
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
	        System.out.println("运行结果:Counter.count=" + counter);  
	    }  
}
