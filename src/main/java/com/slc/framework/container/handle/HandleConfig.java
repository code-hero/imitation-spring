package com.slc.framework.container.handle;

public class HandleConfig {
    private HandleConfig before;
    private HandleConfig next;
    private Handle handle;

    public HandleConfig clone(){
        HandleConfig handleConfig = new HandleConfig();
        handleConfig.setBefore(this.before);
        handleConfig.setNext(this.next);
        handleConfig.setHandle(this.handle);
        return handleConfig;
    }


    public static HandleConfig create(Handle handle){
        HandleConfig handleConfig = new HandleConfig();
        handleConfig.setHandle(handle);
        return handleConfig;
    }

    public HandleConfig getBefore() {
        return before;
    }

    public void setBefore(HandleConfig before) {
        this.before = before;
    }

    public HandleConfig getNext() {
        return next;
    }

    public void setNext(HandleConfig next) {
        this.next = next;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }
}
