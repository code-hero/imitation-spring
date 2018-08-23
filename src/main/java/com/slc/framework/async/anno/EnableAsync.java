package com.slc.framework.async.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAsync {
	Class<? extends Annotation> annotation() default Annotation.class;
}
