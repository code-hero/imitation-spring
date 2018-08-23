package com.slc.framework.container.handle;

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

    public void init(){
        List<HandleConfig> temp = new ArrayList<>();
//        temp.add(HandleConfig.create(new IocHandle()));
//        temp.add(HandleConfig.create(new AsyncHandle()));
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
                }else{
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

    public Object handleAfter(Object result) {
        if (handleConfigs.size() == 0) {
            return result;
        }
        return defaultHandle.doAfterHandle(result);
    }

    public void handleException(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
        if (handleConfigs.size() == 0) {
            throw new RuntimeException(e);
        }
        defaultHandle.exceptionHandle(obj, method, args, proxy, e);
    }
}
