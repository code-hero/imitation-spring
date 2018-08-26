package com.slc.framework.handle.core.time;

import com.slc.framework.handle.core.Handle;
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
        start = System.currentTimeMillis();
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Object result) {
        end = System.currentTimeMillis();
        System.out.println(new StringBuilder("SLC-FRAMEWORK: ").append(obj.getClass().getSuperclass().getSimpleName()).append(".").append(method.getName())
                .append("#").append(" total spent time : ").append(end - start).toString());
        return super.afterHandle(obj, method, args, proxy, result);
    }
}
