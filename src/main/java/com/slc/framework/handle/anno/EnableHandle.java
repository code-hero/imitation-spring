package com.slc.framework.handle.anno;

import com.slc.framework.handle.core.Handle;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHandle {

    boolean defaultHandle() default false;//是否启用默认拦截器

    Class<? extends Handle>[] handleClasses() default {};//自定义拦截器

    Class<? extends Annotation> handleAnno() default RequestMapping.class;

}
