package com.slc.framework.ioc.core;

public class BeanDefinition {
    private Object proxyObject;//代理对象

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
}
