package threadtest;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/*共享资源同步问题*/
public class SimpleResourceManager {
	private final InnerSynchronizer synchronizer;

	public String getSynchronizer() {
		return "排队数量"+synchronizer.getQueueLength()+
				"\n"+synchronizer.getnum();
	}

	// 内部类继承AbstractQueuedSynchronizer，实现（int）共享资源
	private static class InnerSynchronizer extends AbstractQueuedSynchronizer {
		InnerSynchronizer(int numOfResources) {
			// 设置内部状态变量
			setState(numOfResources);
		}
       protected int getnum(){
    	  return getState();
       }
		// 共享模式 参数需要的资源数
		protected int tryAcquireShared(int acquires) {
			for (;;) {
				int available = getState();
				int remain = available - acquires;
				if (remain < 0 || compareAndSetState(available, remain)) {
					// 不够数量 直接返回
					return remain;
				}
			}
		}

		// 共享模式 释放的资源数
		protected boolean tryReleaseShared(int releases) {
			for (;;) {
				int available = getState();
				int next = available + releases;
				if (compareAndSetState(available, next)) {
					return true;
				}
			}
		}
	}

	public SimpleResourceManager(int numOfResources) {
		synchronizer = new InnerSynchronizer(numOfResources);
	}

	public void acquire() throws InterruptedException {
		synchronizer.acquireSharedInterruptibly(1);//会执行tryAcquireShared
	}

	public void release() {
		synchronizer.releaseShared(1);//会执行tryReleaseShared
	}
}
