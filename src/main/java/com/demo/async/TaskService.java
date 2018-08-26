package com.demo.async;

import com.slc.framework.async.anno.Async;
import com.slc.framework.async.core.AsyncResult;
import com.slc.framework.ioc.anno.Service;

import java.util.concurrent.Future;

@Service
public class TaskService {
    @Async
    public Future<String> asyncTask01() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " begin");
        return new AsyncResult<>("finish");
    }

    @Async("defaultAsyncConfigurer")
    public Future<String> asyncTask02() throws Exception {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " begin");
        return new AsyncResult<>("finish");
    }

    @Async("defaultAsyncConfigurer")
    public Future<String> asyncTask03() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " begin");
        int i = 1 / 0;
        return new AsyncResult<>("finish");
    }

    @Async("defaultAsyncConfigurer")
    public void asyncTask04() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " begin");
        throw new Exception("异常测试");
    }
}
