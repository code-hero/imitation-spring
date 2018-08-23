package com.slc.framework.container.handle.async;

import com.slc.framework.container.handle.Handle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AsyncHandle extends Handle {

    @Override
    public boolean beforeHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("AsyncHandle beforeHandle");
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    proxy.invokeSuper(obj, args);
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }
//        }.start();
//        return false;
        return super.beforeHandle(obj, method, args, proxy);
    }

    @Override
    public Object afterHandle(Object result) {
        System.out.println("AsyncHandle afterHandle");
        return super.afterHandle(result);
    }

    @Override
    public void exceptionHandle(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
        System.out.println("AsyncHandle exceptionHandle");
        super.exceptionHandle(obj, method, args, proxy, e);
    }
}
