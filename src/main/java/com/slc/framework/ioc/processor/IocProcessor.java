package com.slc.framework.ioc.processor;

import com.slc.framework.ioc.core.AppContext;

public class IocProcessor {
    public static void config() throws Exception {
        AppContext.getInstance().init();
    }
}
