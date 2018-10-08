package com.slc.framework.ioc;

import com.slc.framework.aop.anno.EnableAop;
import com.slc.framework.aop.processor.AopProcessor;
import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
import com.slc.framework.handle.anno.EnableHandle;
import com.slc.framework.handle.core.HandleFactory;
import com.slc.framework.ioc.anno.EnableIoc;
import com.slc.framework.ioc.processor.IocProcessor;

public enum IocContainer {
    INSTANCE;

    Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();

    public void run(Class<?> clazz) throws Exception {
        //判断是否是Ioc启动类
        if (!clazz.isAnnotationPresent(EnableIoc.class)) {
            throw new Exception("Ioc framework must begin with annotation EnableIoc");
        }
        configuration.setEnableIoc(clazz.getAnnotation(EnableIoc.class));
        //判断是否启动IOC
        IocProcessor.config();

        //判断是否启动AOP注解
        if (clazz.isAnnotationPresent(EnableAop.class)) {
            configuration.setEnableAop(clazz.getAnnotation(EnableAop.class));
            AopProcessor.config();
        }

        //判断是否启动多线程注解
        if (clazz.isAnnotationPresent(EnableAsync.class)) {
            configuration.setEnableAsync(clazz.getAnnotation(EnableAsync.class));
        }

        //判断是否启动拦截器注解
        if (clazz.isAnnotationPresent(EnableHandle.class)) {
            configuration.setEnableHandle(clazz.getAnnotation(EnableHandle.class));
            HandleFactory.getInstance().init();
        }

    }
}
