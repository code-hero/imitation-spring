package com.slc.framework.container.callback;

import com.slc.framework.async.anno.Async;
import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * Created by sunguangzhu on 2018/8/23.
 */
public class DefaultCallbackFilter implements CallbackFilter {

    public static final Callback[] callbacks = new Callback[]{
            NoOp.INSTANCE,
            IocCallback.INSTANCE,
            AsyncCallback.INSTANCE
    };

    public enum CallbackFilterEnum {
        DEFAULT(0),
        IOC(1),
        ASYNC(2);
        private int code;

        CallbackFilterEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }

    private Class<?> clazz;

    public DefaultCallbackFilter(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int accept(Method method) {
        if (method.getDeclaringClass() == clazz) {
            if (method.isAnnotationPresent(Async.class)) {
                Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();
                EnableAsync enableAsync = configuration.getEnableAsync();
                if (enableAsync == null) {
                    return CallbackFilterEnum.DEFAULT.getCode();
                }
                return CallbackFilterEnum.ASYNC.getCode();
            } else {
                return CallbackFilterEnum.IOC.getCode();
            }
        } else {
            return CallbackFilterEnum.DEFAULT.getCode();
        }
    }


}
