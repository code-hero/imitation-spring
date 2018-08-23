package com.slc.framework.async.core;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface AsyncTaskExecutor extends TaskExecutor {

	void execute(Runnable task, long startTimeout);

	Future<?> submit(Runnable task);

	<T> Future<T> submit(Callable<T> task);

}
