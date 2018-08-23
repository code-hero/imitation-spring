
package com.slc.framework.container.callback;

import com.slc.framework.async.anno.Async;
import com.slc.framework.async.core.AsyncConfigurer;
import com.slc.framework.async.core.AsyncConfigurerSupport;
import com.slc.framework.async.core.AsyncUncaughtExceptionHandler;
import com.slc.framework.ioc.core.BeanFactory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by sunguangzhu on 2018/8/23.
 */
public class AsyncCallback implements MethodInterceptor {
    public static final AsyncCallback INSTANCE = new AsyncCallback();

    private AsyncCallback() {
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Async async = method.getAnnotation(Async.class);
        String poolName = async.value();
        ExecutorService executor = null;
        AsyncUncaughtExceptionHandler exceptionHandler=null;
        if (poolName.equals("")) {
            executor = AsyncConfigurerSupport.INSTANCE.getAsyncExecutor();
            exceptionHandler = AsyncConfigurerSupport.INSTANCE.getAsyncUncaughtExceptionHandler();
        } else {
            AsyncConfigurer asyncConfigurer = BeanFactory.getBean(poolName);
            executor = asyncConfigurer.getAsyncExecutor();
            exceptionHandler = asyncConfigurer.getAsyncUncaughtExceptionHandler();
        }
        AsyncUncaughtExceptionHandler finalExceptionHandler = exceptionHandler;
        Callable<Object> task = () -> {
            try {
                Object result = proxy.invokeSuper(obj, args);
                if (result instanceof Future) {
                    return ((Future<?>) result).get();
                }
            } catch (ExecutionException ex) {
                handleError(finalExceptionHandler,ex, method, args);
            } catch (Throwable ex) {
                handleError(finalExceptionHandler,ex, method, args);
            }
            return null;
        };
        return executor.submit(task);

    }

    protected void handleError(AsyncUncaughtExceptionHandler exceptionHandler,Throwable ex, Method method, Object... params) throws Exception {
        if (Future.class.isAssignableFrom(method.getReturnType())) {
            if (ex instanceof Exception) {
                throw (Exception) ex;
            }
            if (ex instanceof Error) {
                throw (Error) ex;
            }
            throw new UndeclaredThrowableException(ex);
        } else {
            // Could not transmit the exception to the caller with default executor
            try {
                exceptionHandler.handleUncaughtException(ex, method, params);
            } catch (Throwable ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
