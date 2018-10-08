package com.slc.framework.container;

import com.slc.framework.ioc.IocContainer;

public class Container {
    public static void run(Class<?> clazz) throws Exception {
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();

        //判断是否是启动类
        if (!clazz.isAnnotationPresent(EntranceApplication.class)) {
            throw new Exception("framework must begin with annotation EntranceApplication");
        }
        configuration.setEntranceApplication(clazz.getAnnotation(EntranceApplication.class));

        IocContainer.INSTANCE.run(clazz);
    }
}
