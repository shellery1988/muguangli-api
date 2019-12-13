package com.muguangli.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonThreadPool {
	private static final ExecutorService newScheduledThreadPool = Executors.newFixedThreadPool(5);

	private static final ExecutorService newSingleThreadPool = Executors.newSingleThreadExecutor();
	
	public static ExecutorService getExecutorService(){
		return newScheduledThreadPool;
	}

	public static ExecutorService getSingleExecutorService(){
		return newSingleThreadPool;
	}
	
	private CommonThreadPool(){
		
	}
}
