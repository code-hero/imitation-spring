package com.slc.framework.async.core;

import java.util.concurrent.ExecutorService;

public interface AsyncConfigurer {

    ExecutorService getAsyncExecutor();

    AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler();

}
