package com.slc.framework.container;

import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.handle.anno.EnableHandle;
import com.slc.framework.handle.core.HandleFactory;
import com.slc.framework.ioc.IocContainer;

public class Container {
    public static void run(Class<?> clazz) throws Exception {
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();

        //判断是否是启动类
        if (!clazz.isAnnotationPresent(EntranceApplication.class)) {
            throw new Exception("framework must begin with annotation EntranceApplication");
        }
        configuration.setEntranceApplication(clazz.getAnnotation(EntranceApplication.class));

        //判断是否启动多线程注解
        if (clazz.isAnnotationPresent(EnableAsync.class)) {
            configuration.setEnableAsync(clazz.getAnnotation(EnableAsync.class));
        }

        //判断是否启动拦截器注解
        if (clazz.isAnnotationPresent(EnableHandle.class)) {
            configuration.setEnableHandle(clazz.getAnnotation(EnableHandle.class));
            HandleFactory.getInstance().init();
        }

        IocContainer.INSTANCE.run(clazz);

    }
}
