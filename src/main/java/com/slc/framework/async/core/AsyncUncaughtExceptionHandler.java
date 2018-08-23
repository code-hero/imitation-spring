package com.slc.framework.async.core;

import java.lang.reflect.Method;

public interface AsyncUncaughtExceptionHandler {
	void handleUncaughtException(Throwable ex, Method method, Object... params);
}
