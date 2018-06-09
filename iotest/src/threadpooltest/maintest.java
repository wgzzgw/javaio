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
	//�̳߳� ����һ���ɻ�����̳߳أ������ǰ�̳߳صĹ�ģ�����˴������󣬽����տյ��̣߳�����������ʱ���������߳��������̳߳ع�ģ�����ơ�
	/*int cpuNums = Runtime.getRuntime().availableProcessors();  //��ȡ��ǰϵͳ��CPU ��Ŀ  
	System.out.println("cpu��Ŀ"+cpuNums);
	ExecutorService executorService2 =Executors.newFixedThreadPool(cpuNums); 
	//ExecutorServiceͨ������ϵͳ��Դ��������̳߳ش�С
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
    ExecutorService exec = Executors.newCachedThreadPool();   //����һ������أ������������СΪInteger.MAX_VALUE  
    for(int i = 0; i < 10; i++) {     
           exec.execute(new Task(String.valueOf(i)));     
    }     
    exec.shutdown();  //ִ�е��˴����������Ϲر��̳߳�,��֮���������̳߳��м��̣߳�����ᱨ��  
    System.out.println("Main: Finished all threads at"+ new Date());  
}  
public static void testFixThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
     ExecutorService exec = Executors.newFixedThreadPool(5);     
     for(int i = 0; i < 10; i++) {     
            exec.execute(new Task(String.valueOf(i)));     
     }     
     exec.shutdown();  //ִ�е��˴����������Ϲر��̳߳�  
     System.out.println("Main Thread: Finished at:"+ new Date());  
}  
public static void testSingleThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
    ExecutorService exec = Executors.newSingleThreadExecutor();   //������СΪ1�Ĺ̶��̳߳�  
    for(int i = 0; i < 10; i++) {     
           exec.execute(new Task(String.valueOf(i)));     
    }     
    exec.shutdown();  //ִ�е��˴����������Ϲر��̳߳�  
    System.out.println("Main Thread: Finished at:"+ new Date());  
}  
public static void testScheduledThreadPool() {  
    System.out.println("Main Thread: Starting at: "+ new Date());    
    ScheduledThreadPoolExecutor  exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);   //������СΪ10���̳߳�  
     for(int i = 0; i < 10; i++) {     
            exec.schedule(new Task(String.valueOf(i)), 10, TimeUnit.SECONDS);//�ӳ�10��ִ��  
     }     
     exec.shutdown();  //ִ�е��˴����������Ϲر��̳߳�  
     while(!exec.isTerminated()){  
            //wait for all tasks to finish  
     }  
     System.out.println("Main Thread: Finished at:"+ new Date());  
}  
/** 
 * ��ʼ���ӳ�0ms��ʼִ�У�ÿ��2000ms����ִ��һ������ 
 * @author linbingwen 
 * @since  2016��6��6�� 
 */  
public static void executeFixedRate() {    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);   
    //����scheduleAtFixedRate��������ִ�������ʱ���������ָ���ļ��ʱ��ʱ������������ָ�����ʱ����һ���µ��̲߳���ִ��������񡣶��ǵȴ����߳�ִ�����
    executor.scheduleAtFixedRate(    
            new Task("pool"),    
            0,    
            500,    
            TimeUnit.MILLISECONDS);    
} 
/**  
 * �Թ̶��ӳ�ʱ�����ִ��  
 * ��������ִ����ɺ���Ҫ�ӳ��趨���ӳ�ʱ�䣬�Ż�ִ���µ�����  
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
 * ��ȡָ��ʱ���Ӧ�ĺ�����  
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
 * ÿ������9��ִ��һ��  
 * ÿ�춨ʱ�����������ִ��  
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
