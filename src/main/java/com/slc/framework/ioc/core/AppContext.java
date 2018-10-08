package com.slc.framework.ioc.core;

import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
import com.slc.framework.container.callback.DefaultCallbackFilter;
import com.slc.framework.ioc.anno.Autowired;
import com.slc.framework.ioc.anno.EnableIoc;
import com.slc.framework.ioc.anno.Service;
import com.slc.framework.util.CommonUtil;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static final AppContext appContext = new AppContext();
    public Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private AppContext() {
    }

    public void init() throws Exception {
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();
        EnableIoc enableIoc = configuration.getEnableIoc();
        String[] scanBasePackages = enableIoc.scanBasePackages();
        for (String scanBasePackage : scanBasePackages) {
            AppContext.getInstance().createBeanDefinitionMap(scanBasePackage);
        }
        autowired();
    }

    public static AppContext getInstance() {
        return appContext;
    }

    private File[] getToDealFiles(String packageName) {
        String packageNamePath = packageName.replaceAll("\\.", "/");

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(packageNamePath);
        assert url != null;
        File urlFile = null;
        try {
            urlFile = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        File[] files = urlFile.listFiles();
        return files;
    }

    public void createBeanDefinitionMap(String packageName) {
        File[] files = getToDealFiles(packageName);
        Map<String, BeanDefinition> map = new HashMap<>();
        for (File file : files)
            try {
                this.createBeanDefinitionMap(packageName, file);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }

    private void createBeanDefinitionMap(String packageName, File file) throws Exception {
        if (file.isFile()) {
            String fileName = file.getName();
            //判断类型<目前只遍历类>
            if (fileName.substring(fileName.lastIndexOf("."), fileName.length()).equals(".class")) {
                if (packageName != "") {
                    packageName += ".";
                }
                String className = packageName + fileName.replace(".class", "");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Service.class)) {
                    initBeanDefinition(clazz);
                }
            }
        } else {
            File[] files = file.listFiles();
            if (packageName != "") {
                packageName += ".";
            }
            packageName += file.getName();
            for (File f : files) {
                createBeanDefinitionMap(packageName, f);
            }
        }
    }

    private void initBeanDefinition(Class<?> clazz) {
        String beanName = CommonUtil.getBeanName(clazz);
        BeanDefinition beanDefinition = new BeanDefinition();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        Callback[] callbacks = DefaultCallbackFilter.callbacks;
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new DefaultCallbackFilter(clazz));
        Object object = enhancer.create();
        beanDefinition.setProxyObject(object);
        beanDefinition.setBeanName(beanName);
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public void autowired() throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Object proxyObject = entry.getValue().getProxyObject();
            Class<?> superclass = proxyObject.getClass().getSuperclass();
            Field[] declaredFields = superclass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = declaredField.getDeclaredAnnotation(Autowired.class);
                    String simpleName;
                    if (autowired.beanName().equals("")) {
                        simpleName = declaredField.getType().getSimpleName();
                    } else {
                        simpleName = autowired.beanName();
                    }
                    Object o2 = beanDefinitionMap.get(simpleName).getProxyObject();
                    if (o2 == null) {
                        throw new Exception("can not find bean " + simpleName);
                    }
                    declaredField.setAccessible(true);
                    declaredField.set(proxyObject, o2);
                }
            }

        }
    }

}
