package com.slc.framework.ioc.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableIoc {
    String[] scanBasePackages() default {};
}
