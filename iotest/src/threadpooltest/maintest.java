package threadpooltest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class maintest {
public static void main(String []args){
/*	ExecutorService executorService=Executors.newCachedThreadPool();*/
	//线程池 创建一个可缓存的线程池，如果当前线程池的规模超出了处理需求，将回收空的线程；当需求增加时，会增加线程数量；线程池规模无限制。
	/*int cpuNums = Runtime.getRuntime().availableProcessors();  //获取当前系统的CPU 数目  
	System.out.println("cpu数目"+cpuNums);
	ExecutorService executorService2 =Executors.newFixedThreadPool(cpuNums); 
	//ExecutorService通常根据系统资源情况灵活定义线程池大小
*/
	/*testCachedThreadPool();*/
	/*testFixThreadPool();*/
	/*testSingleThreadPool();*/
	/*testScheduledThreadPool();*/
	/*executeFixedRate();*/
	/*executeFixedDelay();*/
	executeEightAtNightPerDay();
}
public static void testCachedThreadPool() {  
    System.out.println("Main: Starting at: "+ new Date());    
    ExecutorService exec = Executors.newCachedThreadPool();   //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE  
    for(int i = 0; i < 10; i++) {     
           exec.execute(new Task(String.valueOf(i)));     
    }     
    exec.shutdown();  //执行到此处并不会马上关闭线程池,但之后不能再往线程池中加线程，否则会报错  
    System.out.println("Main: Finished all threads at"+ new Date());  
}  
public static void testFixThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
     ExecutorService exec = Executors.newFixedThreadPool(5);     
     for(int i = 0; i < 10; i++) {     
            exec.execute(new Task(String.valueOf(i)));     
     }     
     exec.shutdown();  //执行到此处并不会马上关闭线程池  
     System.out.println("Main Thread: Finished at:"+ new Date());  
}  
public static void testSingleThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
    ExecutorService exec = Executors.newSingleThreadExecutor();   //创建大小为1的固定线程池  
    for(int i = 0; i < 10; i++) {     
           exec.execute(new Task(String.valueOf(i)));     
    }     
    exec.shutdown();  //执行到此处并不会马上关闭线程池  
    System.out.println("Main Thread: Finished at:"+ new Date());  
}  
public static void testScheduledThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
    ScheduledThreadPoolExecutor  exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);   //创建大小为10的线程池  
     for(int i = 0; i < 10; i++) {     
            exec.schedule(new Task(String.valueOf(i)), 10, TimeUnit.SECONDS);//延迟10秒执行  
     }     
     exec.shutdown();  //执行到此处并不会马上关闭线程池  
     while(!exec.isTerminated()){  
            //wait for all tasks to finish  
     }  
     System.out.println("Main Thread: Finished at:"+ new Date());  
}  
/** 
 * 初始化延迟0ms开始执行，每隔2000ms重新执行一次任务 
 * @author linbingwen 
 * @since  2016年6月6日 
 */  
public static void executeFixedRate() {    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);   
    //对于scheduleAtFixedRate方法，当执行任务的时间大于我们指定的间隔时间时，它并不会在指定间隔时开辟一个新的线程并发执行这个任务。而是等待该线程执行完毕
    executor.scheduleAtFixedRate(    
            new Task("pool"),    
            0,    
            500,    
            TimeUnit.MILLISECONDS);    
} 
/**  
 * 以固定延迟时间进行执行  
 * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务  
 */    
public static void executeFixedDelay() {    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);    
    executor.scheduleWithFixedDelay(    
            new Task("pool"),    
            0,    
            2000,    
            TimeUnit.MILLISECONDS);    
}    
/**  
 * 获取指定时间对应的毫秒数  
 * @param time "HH:mm:ss"  
 * @return  
 */    
private static long getTimeMillis(String time) {    
    try {    
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");    
        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");    
        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);    
        return curDate.getTime();    
    } catch (ParseException e) {    
        e.printStackTrace();    
    }    
    return 0;    
}   
/**  
 * 每天晚上9点执行一次  
 * 每天定时安排任务进行执行  
 */    
public static void executeEightAtNightPerDay() {    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);    
    long oneDay = 24 * 60 * 60 * 1000;    
    long initDelay  = getTimeMillis("23:05:00") - System.currentTimeMillis();    
    initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;    
    
    executor.scheduleAtFixedRate(    
            new Task("pool"),    
            initDelay,    
            oneDay,    
            TimeUnit.MILLISECONDS);    
}    
}
