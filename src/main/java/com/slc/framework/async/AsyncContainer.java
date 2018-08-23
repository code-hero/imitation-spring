package com.slc.framework.async;

import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.async.processor.AsyncProcessor;
import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;

public enum AsyncContainer {
    INSTANCE;
    Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();

    public void run(Class<?> clazz) throws Exception {
        //判断是否是Asynco启动类
        if (!clazz.isAnnotationPresent(EnableAsync.class)) {
            throw new Exception("enableAsync framework must begin with annotation EnableAsync");
        }
        configuration.setEnableAsync(clazz.getAnnotation(EnableAsync.class));
        AsyncProcessor.config();

    }
}
