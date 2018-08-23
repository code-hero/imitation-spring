package com.slc.framework.ioc.core;

public class BeanFactory {

    public static <T> T getBean(String beanName) {
        T proxyObject = (T) AppContext.getInstance().beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        T proxyObject = (T) AppContext.getInstance().beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        T proxyObject = (T) AppContext.getInstance().beanDefinitionMap.get(beanName).getProxyObject();
        return proxyObject;
    }
}
