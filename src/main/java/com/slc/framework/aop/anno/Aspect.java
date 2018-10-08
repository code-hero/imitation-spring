package com.slc.framework.aop.anno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {

    String pointcut() default "";

    Class<? extends Annotation> annotation() default Annotation.class;
}
