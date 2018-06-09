package threadtest;
public class Counter {  
    public volatile static int count = 0;  
    public synchronized  static void inc() {   
    	try {
    		while(true)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        count++;  
    }  
    public static void main(String[] args) throws InterruptedException   {  
    	Thread t=new Thread(new Runnable() {  
            @Override  
            public void run() {  
                Counter.inc();  
            }  
        }); 
        t.start();  
        t.interrupt(); 
        
        Thread.sleep(1000);
        System.out.println("���н��:Counter.count=" + Counter.count);  
    }  
}  