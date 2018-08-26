package com.slc.framework.async.core;

import java.lang.reflect.Method;

public class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("ex = [" + ex + "], method = [" + method + "], params = [" + params + "]");
    }

}
