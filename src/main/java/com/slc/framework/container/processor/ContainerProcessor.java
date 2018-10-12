package com.slc.framework.container.processor;

import com.slc.framework.aop.core.AdviceDefinition;
import com.slc.framework.aop.core.AdviceFactory;
import com.slc.framework.aop.core.JoinPoint;
import com.slc.framework.handle.anno.RequestMapping;
import com.slc.framework.handle.core.HandleFactory;
import com.slc.framework.ioc.core.BeanFactory;
import com.slc.framework.util.CommonUtil;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContainerProcessor {
    /**
     * 核心方法
     *
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    public static Object config(Object obj, Method method, Object[] args, MethodProxy proxy) {
        //Function handle
        if (needConfigHandle(method)) {
            return configHandle(obj, method, args, proxy);
        }
        //Function aop
        if (needConfigAop(method)) {
            return configAop(obj, method, args, proxy);
        }
        return doRealMethod(obj, method, args, proxy);
    }

    private static boolean needConfigAop(Method method) {
        String methodFullName = CommonUtil.getFullMethodName(method);
        AdviceDefinition adviceDefinition = AdviceFactory.getInstance().adviceDefinitionMap.get(methodFullName);
        if (adviceDefinition != null) {
            return true;
        }
        return false;
    }

    private static Object configAop(Object obj, Method method, Object[] args, MethodProxy proxy) {
        AdviceDefinition adviceDefinition = AdviceFactory.getInstance().adviceDefinitionMap.get(CommonUtil.getFullMethodName(method));
        Object result = null;

        try {
            //执行前置通知
            if (adviceDefinition.isHasBefore()) {
                List<Method> beforeMethods = adviceDefinition.getBeforeMethods();
                for (Method beforeMethod : beforeMethods) {
                    Object bean = BeanFactory.getBean(adviceDefinition.getBeanNames().get(beforeMethods.indexOf(beforeMethod)));
                    beforeMethod.invoke(bean);
                }
            }
            //执行环绕通知
            if (adviceDefinition.isHasAround()) {
                List<Method> aroundMethods = adviceDefinition.getAroundMethods();
                for (Method aroundMethod : aroundMethods) {
                    JoinPoint joinPoint = new JoinPoint(obj, method, args, proxy);
                    Object bean = BeanFactory.getBean(adviceDefinition.getBeanNames().get(aroundMethods.indexOf(aroundMethod)));
                    aroundMethod.invoke(bean, joinPoint);
                    break;//多个around 只执行第一个around
                }
            } else {
                //执行目标方法
                result = doRealMethod(obj, method, args, proxy);
            }
            // 执行后置通知
            if (adviceDefinition.isHasAfter()) {
                List<String> beanNamesCopy = new ArrayList<>(adviceDefinition.getBeanNames());
                Collections.reverse(beanNamesCopy);

                List<Method> afterMethods = adviceDefinition.getAfterMethods();
                for (Method afterMethod : afterMethods) {
                    Object bean = BeanFactory.getBean(beanNamesCopy.get(afterMethods.indexOf(afterMethod)));
                    afterMethod.invoke(bean);
                }
            }

        } catch (Exception e) {
            // 执行返回通知
            if (adviceDefinition.isHasAfterThrowing()) {
                List<Method> afterThrowingMethods = adviceDefinition.getAfterThrowingMethods();
                for (Method afterThrowingMethod : afterThrowingMethods) {
                    Object bean = BeanFactory.getBean(adviceDefinition.getBeanNames().get(afterThrowingMethods.indexOf(afterThrowingMethod)));
                    try {
                        afterThrowingMethod.invoke(bean, e);
                    } catch (Exception ee) {
                        throw new RuntimeException(ee);
                    }
                }
            }
        } finally {
            // 执行返回通知
            if (adviceDefinition.isHasAfterReturning()) {
                List<String> beanNamesCopy = new ArrayList<>(adviceDefinition.getBeanNames());
                Collections.reverse(beanNamesCopy);

                List<Method> afterReturningMethods = adviceDefinition.getAfterReturningMethods();
                for (Method afterReturningMethod : afterReturningMethods) {
                    Object bean = BeanFactory.getBean(beanNamesCopy.get(afterReturningMethods.indexOf(afterReturningMethod)));

                    try {
                        afterReturningMethod.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        return result;
    }


    /**
     * 判断是否需要启用拦截器
     * 目前拦截所有标有RequestMapping注解的方法
     *
     * @param method
     * @return
     */
    private static boolean needConfigHandle(Method method) {
        return method.isAnnotationPresent(RequestMapping.class);
    }


    /**
     * 处理拦截器
     *
     * @param obj
     * @param method
     * @param args
     * @param proxy
     */
    private static Object configHandle(Object obj, Method method, Object[] args, MethodProxy proxy) {
        //Function aop
        if (!HandleFactory.getInstance().handleBefore(obj, method, args, proxy)) {
            return null;
        }
        Object result = doRealMethod(obj, method, args, proxy);
        return HandleFactory.getInstance().handleAfter(obj, method, args, proxy, result);
    }

    /**
     * 执行真正的方法
     *
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     */
    private static Object doRealMethod(Object obj, Method method, Object[] args, MethodProxy proxy) {
        Object result = null;
        try {
            result = proxy.invokeSuper(obj, args);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }
}
