package threadtest;

public class ResourceTest {
	static SimpleResourceManager s=new SimpleResourceManager(3);//3̨������Դ
	  public static void main(String[] args) throws InterruptedException  {  
		    	new Thread(new Runnable() {  
		            @Override  
		            public void run() {  
		            	try {
		            		System.out.println(s.getSynchronizer());
							s.acquire();
						} catch (InterruptedException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}//Ҫһ̨
		            	System.out.println(s.getSynchronizer());
		            }  
		        }).start();
		    	new Thread(new Runnable() {  
		            @Override  
		            public void run() {  
		            	try {
		            		System.out.println(s.getSynchronizer());
							s.acquire();
						} catch (InterruptedException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}//Ҫһ̨
		            	System.out.println(s.getSynchronizer());
		            }  
		        }).start(); 
		    	new Thread(new Runnable() {  
		            @Override  
		            public void run() {  
		            	try {
		            		System.out.println(s.getSynchronizer());
							s.acquire();
						} catch (InterruptedException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}//Ҫһ̨
		            	System.out.println(s.getSynchronizer());
		            }  
		        }).start(); 
		    	Thread.sleep(1000);
		    	s.release();//�ͷ�һ̨
				s.release();//�ͷ�һ̨
				s.release();//�ͷ�һ̨
				Thread.sleep(1000);
		    	new Thread(new Runnable() {  
		            @Override  
		            public void run() {  
		            	try {
		            		System.out.println(s.getSynchronizer());
							s.acquire();
						} catch (InterruptedException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}//Ҫһ̨
		            	System.out.println(s.getSynchronizer());
		            }  
		        }).start(); 
	  }
}