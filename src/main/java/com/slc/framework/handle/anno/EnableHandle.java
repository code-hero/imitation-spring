package com.slc.framework.handle.anno;

import com.slc.framework.handle.core.Handle;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHandle {
    boolean defaultHandle() default false;

    Class<? extends Handle>[] handleClasses() default {};
}
