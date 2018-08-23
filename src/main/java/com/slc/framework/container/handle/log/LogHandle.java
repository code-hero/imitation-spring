package com.slc.framework.container.handle.log;

import com.slc.framework.container.handle.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 日志处理
 */
public class LogHandle extends Handle {
    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("TimeHandle beforeHandle");

        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object result) {
        System.out.println("TimeHandle afterHandle");
        return super.afterHandle(result);
    }
}
