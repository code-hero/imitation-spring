package com.slc.framework.container;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntranceApplication {

    String[] scanBasePackages() default {};
}
