package com.slc.framework.ioc;

import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
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
    }
}
