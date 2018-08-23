package com.slc.framework.ioc.core;

import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
import com.slc.framework.container.EntranceApplication;
import com.slc.framework.container.callback.DefaultCallbackFilter;
import com.slc.framework.ioc.anno.Autowired;
import com.slc.framework.ioc.anno.Service;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sxlju_000
 * Date: 13-10-22
 * Time: 下午11:48
 * To change this template use File | Settings | File Templates.
 */
public class AppContext {
    private static final AppContext appContext = new AppContext();
    public Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private AppContext() {
    }

    public void init() throws Exception {
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();
        EntranceApplication entranceApplication = configuration.getEntranceApplication();
        String[] scanBasePackages = entranceApplication.scanBasePackages();
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
        final Service annotation = clazz.getAnnotation(Service.class);
        String key = null;
        if (annotation.value() == null || "".equals(annotation.value())) {
            key = clazz.getSimpleName();
        } else {
            key = annotation.value();
        }
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setOrigClass(clazz);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        Callback[] callbacks = DefaultCallbackFilter.callbacks;
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new DefaultCallbackFilter(clazz));
        Object object = enhancer.create();
        beanDefinition.setProxyObject(object);
        beanDefinitionMap.put(key, beanDefinition);
    }

    public void autowired() throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Object proxyObject = entry.getValue().getProxyObject();
            Class<?> superclass = proxyObject.getClass().getSuperclass();
            Field[] declaredFields = superclass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    String simpleName = declaredField.getType().getSimpleName();
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
