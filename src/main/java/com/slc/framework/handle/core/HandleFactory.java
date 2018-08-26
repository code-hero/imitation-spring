package com.slc.framework.handle.core;

import com.slc.framework.container.Configuration;
import com.slc.framework.container.ConfigurationFactory;
import com.slc.framework.handle.anno.EnableHandle;
import com.slc.framework.handle.core.log.LogHandle;
import com.slc.framework.handle.core.time.TimeHandle;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HandleFactory {
    public static List<HandleConfig> handleConfigs = new ArrayList<>();
    public static DefaultHandle defaultHandle = new DefaultHandle();
    public static HandleConfig firstHandleConfig = new HandleConfig();
    public static HandleConfig lastHandleConfig = new HandleConfig();
    private static final HandleFactory handleFactory = new HandleFactory();

    public void init() {
        List<Handle> handlesToDeal = new ArrayList<>();
        Configuration configuration = ConfigurationFactory.INSTANCE.loadConfiguration();
        EnableHandle enableHandle = configuration.getEnableHandle();
        if(enableHandle!=null){
            boolean defaultHandle = enableHandle.defaultHandle();
            if(defaultHandle){
                handlesToDeal.add(new TimeHandle());
                handlesToDeal.add(new LogHandle());
            }
            Class<? extends Handle>[] handleClasses = enableHandle.handleClasses();
            //todo 去重// 暂时考虑不会定义系统中的Handle
            for (Class<? extends Handle> handleClass : handleClasses) {
                try {
                    handlesToDeal.add(handleClass.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        List<HandleConfig> temp = new ArrayList<>();
        for (Handle handle : handlesToDeal) {
            temp.add(HandleConfig.create(handle));
        }
        buildHandleConfig(temp);
    }

    private void buildHandleConfig(List<HandleConfig> temp) {
        for (int i = 0; i < temp.size(); i++) {
            HandleConfig handleConfig = temp.get(i);
            if (i == 0) {
                HandleFactory.firstHandleConfig.setHandle(handleConfig.getHandle());
                if (i != temp.size() - 1) {
                    handleConfig.setNext(temp.get(i + 1));
                    HandleFactory.firstHandleConfig.setNext(handleConfig.getNext());
                } else {
                    HandleFactory.lastHandleConfig.setHandle(handleConfig.getHandle());
                }
            } else if (i == temp.size() - 1) {
                handleConfig.setBefore(temp.get(i - 1));
                HandleFactory.lastHandleConfig.setHandle(handleConfig.getHandle());
                HandleFactory.lastHandleConfig.setBefore(handleConfig.getBefore());
            } else {
                handleConfig.setNext(temp.get(i + 1));
                handleConfig.setBefore(temp.get(i - 1));
            }
            this.handleConfigs.add(handleConfig);
        }

    }

    private HandleFactory() {
    }

    public static HandleFactory getInstance() {
        return handleFactory;
    }

    public boolean handleBefore(Object obj, Method method, Object[] args, MethodProxy proxy) {
        if (handleConfigs.size() == 0) {
            return true;
        }
        return defaultHandle.doBeforeHandle(obj, method, args, proxy);
    }

    public Object handleAfter(Object obj, Method method, Object[] args, MethodProxy proxy, Object result) {
        if (handleConfigs.size() == 0) {
            return result;
        }
        return defaultHandle.doAfterHandle(obj, method, args, proxy, result);
    }

    public void handleException(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
        if (handleConfigs.size() == 0) {
            throw new RuntimeException(e);
        }
        defaultHandle.exceptionHandle(obj, method, args, proxy, e);
    }
}
