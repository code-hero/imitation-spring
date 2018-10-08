package com.demo;

import com.demo.aop.AopService;
import com.demo.async.TaskService;
import com.demo.handle.MyHandle;
import com.demo.ioc.UserService;
import com.slc.framework.aop.anno.EnableAop;
import com.slc.framework.async.anno.Async;
import com.slc.framework.async.anno.EnableAsync;
import com.slc.framework.container.Container;
import com.slc.framework.container.EntranceApplication;
import com.slc.framework.handle.anno.EnableHandle;
import com.slc.framework.handle.core.time.TimeHandle;
import com.slc.framework.ioc.anno.EnableIoc;
import com.slc.framework.ioc.core.BeanFactory;

import java.util.concurrent.Future;

@EnableAsync(annotation = Async.class)
@EnableIoc(scanBasePackages = "com.demo")//必须
@EnableAop
@EnableHandle(defaultHandle = true, handleClasses = {TimeHandle.class, MyHandle.class})
@EntranceApplication
public class Application {

    public static void main(String[] args) throws Exception {
        Container.run(Application.class);
        yourCode();
        System.in.read(); // 按任意键退出
    }

    //测试方法
    private static void yourCode() throws Exception {
//        iocTest();
//        asyncTest01();
//        asyncTest02();
//        asyncTest03();
//        asyncTest04();
//        aopTest();
//        aopTestByAnno();
    }

    private static void asyncTest01() throws Exception {
        TaskService taskService = BeanFactory.getBean(TaskService.class);
        Future<String> future = taskService.asyncTask01();
        while (true) {
            if (future.isDone()) {
                System.out.println("future.get() = " + future.get());
                break;
            }
            Thread.sleep(500);
        }
        System.out.println("taskService = " + taskService);
    }

    private static void asyncTest02() throws Exception {
        TaskService taskService = BeanFactory.getBean(TaskService.class);
        for (int i = 0; i <= 20; i++) {
            taskService.asyncTask02();
        }
        System.out.println("taskService = " + taskService);
    }

    private static void asyncTest03() throws Exception {
        TaskService taskService = BeanFactory.getBean(TaskService.class);
        Future<String> future = taskService.asyncTask03();
        while (true) {
            if (future.isDone()) {
                System.out.println("future.get() = " + future.get());
                break;
            }
            Thread.sleep(500);
        }
        System.out.println("taskService = " + taskService);
    }

    private static void asyncTest04() throws Exception {
        TaskService taskService = BeanFactory.getBean(TaskService.class);
        taskService.asyncTask04();
        System.out.println("taskService = " + taskService);
    }


    private static void iocTest() {
        UserService userService = BeanFactory.getBean("UserService", UserService.class);
        userService.sayHello();
    }

    private static void aopTest() {
        AopService aopService = BeanFactory.getBean("AopService", AopService.class);
        aopService.sayHello();
    }
    private static void aopTestByAnno() {
        AopService aopService = BeanFactory.getBean("AopService", AopService.class);
        aopService.bb();
    }
}
