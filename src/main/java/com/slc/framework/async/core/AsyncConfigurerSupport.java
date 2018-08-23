package com.slc.framework.async.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncConfigurerSupport implements AsyncConfigurer {

    public static final AsyncConfigurerSupport INSTANCE = new AsyncConfigurerSupport();
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static final AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler = new SimpleAsyncUncaughtExceptionHandler();
    private AsyncConfigurerSupport(){
    }

	@Override
	public ExecutorService getAsyncExecutor() {
		return executorService;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return asyncUncaughtExceptionHandler;
	}

}
