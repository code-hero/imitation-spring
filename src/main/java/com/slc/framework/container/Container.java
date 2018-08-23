package com.slc.framework.container;

import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.async.processor.AsyncProcessor;
import com.slc.framework.container.handle.HandleFactory;
import com.slc.framework.ioc.anno.EnableIoc;
import com.slc.framework.ioc.processor.IocProcessor;

public class Container {
    public static void run(Class<?> clazz) throws Exception {
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();

        //判断是否是启动类
        if(!clazz.isAnnotationPresent(EntranceApplication.class)){
            throw new Exception("framework must begin with annotation EntranceApplication");
        }
        configuration.setEntranceApplication(clazz.getAnnotation(EntranceApplication.class));
        //判断是否启动IOC
        if(clazz.isAnnotationPresent(EnableIoc.class)){
            IocProcessor.config();
            configuration.setEnableIoc(clazz.getAnnotation(EnableIoc.class));
        }
        //判断是否启动多线程注解
        if(clazz.isAnnotationPresent(EnableAsync.class)){
            AsyncProcessor.config();
            configuration.setEnableAsync(clazz.getAnnotation(EnableAsync.class));
        }

        HandleFactory.getInstance().init();
    }
}
