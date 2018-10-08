package com.slc.framework.container.callback;

import com.slc.framework.container.processor.ContainerProcessor;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by sunguangzhu on 2018/8/23.
 */
public class IocCallback implements MethodInterceptor {
    public static final IocCallback INSTANCE = new IocCallback();

    private IocCallback() {

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        return ContainerProcessor.config(obj, method, args, proxy);
    }
}
