package com.demo.threadpool.config;

import com.slc.framework.async.core.AsyncConfigurer;
import com.slc.framework.async.core.AsyncUncaughtExceptionHandler;
import com.slc.framework.ioc.anno.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service(value = "defaultAsyncConfigurer")
public class DefaultAsyncConfigurer implements AsyncConfigurer {
    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50));    //7 9
    public static final AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler = (ex, method, params) -> {
        System.out.println("defaultAsyncConfigurer.getAsyncUncaughtExceptionHandler=================");
        System.out.println("ex = [" + ex + "], method = [" + method + "], params = [" + params + "]");
    };

    @Override
    public ExecutorService getAsyncExecutor() {
        return executor;
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncUncaughtExceptionHandler;
    }

}
