package com.slc.framework.ioc.core;

public class BeanDefinition {
    private String beanName;//key名称
    private Object proxyObject;//代理对象

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
}
