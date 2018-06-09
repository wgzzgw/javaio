package threadtest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockIdGenrator{  
	//new ReentrantLock(true)是重载，使用更加公平的加锁机制，在锁被释放后，会优先给等待时间最长的线程，避免一些线程长期无法获得锁  
	   private static  ReentrantLock lock= new ReentrantLock();  
	   private static int value = 0;  
	   public static int getNext() throws InterruptedException{  
	      lock.lock();      //进来就加锁，没有锁会等待  
	      try{  
	    	  while(value==5){
	    		  Condition c=lock.newCondition();
	    		  c.await();
	    	  }
	         return value++;//实际操作  
	      }finally{  
	         lock.unlock();//释放锁  
	      }  
	   }  
	   public static void main(String[] args) throws InterruptedException   {  
		   for(int i=0;i<10;i++){
	    	new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	try {
						getNext();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
	            }  
	        }).start(); 
		   }
	        Thread.sleep(1000);
	        System.out.println("运行结果:value=" + value);  
	    }  
	}  