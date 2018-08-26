package com.slc.framework.handle.core.log;

import com.slc.framework.handle.core.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 日志处理
 */
public class LogHandle extends Handle {
    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println(new StringBuilder("SLC-FRAMEWORK: ").append(obj.getClass().getSuperclass().getSimpleName()).append(".").append(method.getName())
                .append("#").append(" begin ").toString());
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Object result) {
        System.out.println(new StringBuilder("SLC-FRAMEWORK: ").append(obj.getClass().getSuperclass().getSimpleName()).append(".").append(method.getName())
                .append("#").append(" end ").toString());
        return super.afterHandle(obj, method, args, proxy, result);
    }
}
