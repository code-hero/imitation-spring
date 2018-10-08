package com.slc.framework.util;

import com.slc.framework.ioc.anno.Service;

import java.lang.reflect.Method;

public class CommonUtil {
    public static String getFullMethodName(Method method) {
        return method.getDeclaringClass().getName() + "." + method.getName();
    }

    public static String getBeanName(Class<?> clazz) {
        final Service annotation = clazz.getAnnotation(Service.class);
        String beanName = null;
        if (annotation.value() == null || "".equals(annotation.value())) {
            beanName = clazz.getSimpleName();
        } else {
            beanName = annotation.value();
        }
        return beanName;
    }
}
