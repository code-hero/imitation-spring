package com.slc.framework.container;

import com.slc.framework.aop.anno.EnableAop;
import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.handle.anno.EnableHandle;
import com.slc.framework.ioc.anno.EnableIoc;

public class Configuration {

    private EntranceApplication entranceApplication;
    private EnableIoc enableIoc;
    private EnableAsync enableAsync;
    private EnableHandle enableHandle;
    private EnableAop enableAop;

    public EntranceApplication getEntranceApplication() {
        return entranceApplication;
    }

    public void setEntranceApplication(EntranceApplication entranceApplication) {
        this.entranceApplication = entranceApplication;
    }

    public EnableIoc getEnableIoc() {
        return enableIoc;
    }

    public void setEnableIoc(EnableIoc enableIoc) {
        this.enableIoc = enableIoc;
    }

    public EnableAsync getEnableAsync() {
        return enableAsync;
    }

    public void setEnableAsync(EnableAsync enableAsync) {
        this.enableAsync = enableAsync;
    }

    public EnableHandle getEnableHandle() {
        return enableHandle;
    }

    public void setEnableHandle(EnableHandle enableHandle) {
        this.enableHandle = enableHandle;
    }

    public EnableAop getEnableAop() {
        return enableAop;
    }

    public void setEnableAop(EnableAop enableAop) {
        this.enableAop = enableAop;
    }
}
