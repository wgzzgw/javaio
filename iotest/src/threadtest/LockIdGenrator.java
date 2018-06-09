package threadtest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockIdGenrator{  
	//new ReentrantLock(true)�����أ�ʹ�ø��ӹ�ƽ�ļ������ƣ��������ͷź󣬻����ȸ��ȴ�ʱ������̣߳�����һЩ�̳߳����޷������  
	   private static  ReentrantLock lock= new ReentrantLock();  
	   private static int value = 0;  
	   public static int getNext() throws InterruptedException{  
	      lock.lock();      //�����ͼ�����û������ȴ�  
	      try{  
	    	  while(value==5){
	    		  Condition c=lock.newCondition();
	    		  c.await();
	    	  }
	         return value++;//ʵ�ʲ���  
	      }finally{  
	         lock.unlock();//�ͷ���  
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
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}  
	            }  
	        }).start(); 
		   }
	        Thread.sleep(1000);
	        System.out.println("���н��:value=" + value);  
	    }  
	}  