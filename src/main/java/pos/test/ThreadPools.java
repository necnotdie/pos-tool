package pos.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {
	private ThreadPoolExecutor threadPoolExecutor;
	private int corePoolSize = 0;
	private int maximumPoolSize = 0;
	private long keepAliveTime = 0;
	private TimeUnit unit;
	private BlockingQueue<Runnable> workQueue;
	private ThreadFactory threadFactory;
	private RejectedExecutionHandler handler;
	private long RunningTime = 0;
	private long ThreadWaitTime = 0;
	private boolean allowCoreThreadTimeOut = false;
	private Thread timeThread;
	private Date StartTime;
	private List<Runnable> allRunnables;
	public ThreadPools() {
		if(allRunnables==null){
			allRunnables = new ArrayList<Runnable>();
		}
		StartTime = new Date();
		if(timeThread==null){
			timeThread = new Thread(){
				public void run() {
					while(true){
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
						}
						if((new Date().getTime()-StartTime.getTime())>=RunningTime){
//							System.out.println(workQueue.size());
							System.out.println("ActiveCount===="+threadPoolExecutor.getActiveCount());
//							HashSet hashSet = getAliveThreads();
//							Thread runnable = null ;
//							System.out.println("=====hash"+hashSet.size());
//							for (Object object : hashSet) {
//								Field[] fields = object.getClass().getDeclaredFields();
//								for (Field field : fields) {
//									if(!field.isAccessible()&&"firstTask".equals(field.getName())){
//										field.setAccessible(true);
//										try {
//											runnable = (Thread) field.get(object);
//										} catch (IllegalArgumentException e) {
//											e.printStackTrace();
//										} catch (IllegalAccessException e) {
//											e.printStackTrace();
//										}
//										System.out.println("==="+runnable.getName());
//									}
//								}
//							}
							System.out.println(allRunnables.size());
							for (Runnable runnable : allRunnables) {
								Thread thread = (Thread) runnable;
//									thread.wait(10000);
								thread.interrupt();
							}
							threadPoolExecutor.shutdownNow();
						}
					}
				};
			};
		}
	}
	public void init() {
		System.out.println(timeThread.isAlive());
		timeThread.start();
		System.out.println(timeThread.isAlive());
		if(workQueue==null){
			workQueue = new LinkedBlockingDeque<Runnable>();
		}
		if(this.threadPoolExecutor==null){
			if(threadFactory==null&&handler==null){
				this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			}else if(threadFactory!=null&&handler==null){
				this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
			}else if(threadFactory==null&&handler!=null){
				this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
			}else if(threadFactory!=null&&handler!=null){
				this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
			}
		}
		threadPoolExecutor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
	}
	public void execute(Thread command) throws InterruptedException{
		Thread.sleep(ThreadWaitTime);
//		command.join(RunningTime);
//		System.out.println(command);
		this.threadPoolExecutor.execute(command);
		allRunnables.add(command);
	}
	public HashSet getAliveThreads(){
		Field[] fields = ThreadPoolExecutor.class.getDeclaredFields();
		HashSet workers = null;
		for (Field field : fields) {
			if(!field.isAccessible()&&"workers".equals(field.getName())){
				field.setAccessible(true);
				Object obj = null;
				try {
					obj = field.get(threadPoolExecutor);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				workers = (HashSet) obj;
			}
		}
		return workers;
	}
	public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}
	public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		this.workQueue = workQueue;
	}
	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}
	public void setHandler(RejectedExecutionHandler handler) {
		this.handler = handler;
	}
	public void setRunningTime(long runningTime) {
		RunningTime = runningTime;
	}
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
	}
	public static void main(String[] args) {
		ThreadPools pools = new ThreadPools();
		pools.setCorePoolSize(10);
		pools.setMaximumPoolSize(15);
		pools.setKeepAliveTime(1);
		pools.setUnit(TimeUnit.SECONDS);
		pools.setRunningTime(10000);
		pools.setAllowCoreThreadTimeOut(true);
		pools.init();
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(){
				public void run() {
					for (int j = 0; j < 5; j++) {
						try {
							Thread.sleep(5000);
						} catch (Exception e) {
						}
						System.out.println("thread "+this.getName()+"====="+j);
					}
				};
			};
			thread.setName(i+"");
			try {
				pools.execute(thread);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
