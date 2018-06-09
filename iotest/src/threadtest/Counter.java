package threadtest;
public class Counter {  
    public volatile static int count = 0;  
    public synchronized  static void inc() {   
    	try {
    		while(true)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
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
        System.out.println("运行结果:Counter.count=" + Counter.count);  
    }  
}  