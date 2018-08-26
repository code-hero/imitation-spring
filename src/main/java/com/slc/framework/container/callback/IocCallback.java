package com.slc.framework.container.callback;

import com.slc.framework.handle.core.HandleFactory;
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
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (!HandleFactory.getInstance().handleBefore(obj, method, args, proxy)) {
            return null;
        }
        Object result = null;
        try {
            result = proxy.invokeSuper(obj, args);
        } catch (Exception e) {
            HandleFactory.getInstance().handleException(obj, method, args, proxy, e);
        }
        return HandleFactory.getInstance().handleAfter(obj, method, args, proxy, result);

    }
}
