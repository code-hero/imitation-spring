package com.slc.framework.container.handle.time;

import com.slc.framework.container.handle.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 执行时间
 */
public class TimeHandle extends Handle {
    private long start;
    private long end;
    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("TimeHandle beforeHandle");
        start=System.currentTimeMillis();
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object result) {
        System.out.println("TimeHandle afterHandle");
        end=System.currentTimeMillis();
        System.out.println("result = " + result);
        return super.afterHandle(result);
    }
}
