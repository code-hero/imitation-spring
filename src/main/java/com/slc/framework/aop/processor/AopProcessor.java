package com.slc.framework.aop.processor;

import com.slc.framework.aop.anno.*;
import com.slc.framework.aop.core.AdviceDefinition;
import com.slc.framework.aop.core.AdviceFactory;
import com.slc.framework.ioc.core.AppContext;
import com.slc.framework.ioc.core.BeanDefinition;
import com.slc.framework.util.CommonUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class AopProcessor {
    public static void config() {
        List<Class<?>> aspectClasses = new ArrayList<>();
        for (BeanDefinition beanDefinition : AppContext.getInstance().beanDefinitionMap.values()) {
            Class<?> superclass = beanDefinition.getProxyObject().getClass().getSuperclass();
            if (superclass.isAnnotationPresent(Aspect.class)) {
                aspectClasses.add(superclass);
            }
        }

        if (aspectClasses.size() == 0) {
            return;
        }

        for (BeanDefinition beanDefinition : AppContext.getInstance().beanDefinitionMap.values()) {
            Class<?> superclass = beanDefinition.getProxyObject().getClass().getSuperclass();
            Method[] declaredMethods = superclass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                String methodFullName = CommonUtil.getFullMethodName(declaredMethod);
                for (Class<?> aspectClass : aspectClasses) {
                    Aspect aspect = aspectClass.getDeclaredAnnotation(Aspect.class);
                    String beanName = CommonUtil.getBeanName(aspectClass);
                    String pointcut = aspect.pointcut();
                    boolean isMatch = Pattern.matches(pointcut, methodFullName) || isMatchByAnno(aspect.annotation(), declaredMethod);
                    if (isMatch) {
                        buildAdviceDefinitionMap(methodFullName, aspectClass, beanName);
                    }
                }
            }
        }


    }

    private static boolean isMatchByAnno(Class<? extends Annotation> annotation, Method declaredMethod) {
        return declaredMethod.isAnnotationPresent(annotation);
    }

    private static void buildAdviceDefinitionMap(String methodFullName, Class<?> aspectClass, String beanName) {
        AdviceDefinition adviceDefinition = AdviceFactory.getInstance().adviceDefinitionMap.get(methodFullName);
        if (adviceDefinition == null) {
            adviceDefinition = new AdviceDefinition();
            adviceDefinition.setBeanName(beanName);
            AdviceFactory.getInstance().adviceDefinitionMap.put(methodFullName, adviceDefinition);

        }

        Method[] declaredMethods = aspectClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Before.class)) {
                adviceDefinition.setHasBefore(true);
                adviceDefinition.getBeforeMethods().add(declaredMethod);
            }
            if (declaredMethod.isAnnotationPresent(After.class)) {
                adviceDefinition.setHasAfter(true);
                adviceDefinition.getAfterMethods().add(declaredMethod);
                Collections.reverse(adviceDefinition.getAfterMethods());
            }
            if (declaredMethod.isAnnotationPresent(AfterReturning.class)) {
                adviceDefinition.setHasAfterReturning(true);
                adviceDefinition.getAfterReturningMethods().add(declaredMethod);
                Collections.reverse(adviceDefinition.getAfterReturningMethods());
            }
            //待优化，目前最好只定义一个异常
            if (declaredMethod.isAnnotationPresent(AfterThrowing.class)) {
                adviceDefinition.setHasAfterThrowing(true);
                adviceDefinition.getAfterThrowingMethods().add(declaredMethod);
            }
            //待优化，目前最好只定义一个环绕
            if (declaredMethod.isAnnotationPresent(Around.class)) {
                adviceDefinition.setHasAround(true);
                adviceDefinition.getAroundMethods().add(declaredMethod);
            }
        }
    }
}
