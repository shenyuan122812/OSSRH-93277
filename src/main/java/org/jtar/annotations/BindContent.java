package org.jtar.annotations;

import java.lang.annotation.*;

/**
 * author Administrator
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindContent {

    String dataFileName()default "";

    String caseId() default "";

    String uploadFileName()default "";
}
