package org.jtar.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HttpBase {
    HttpMethodEnum httpMethod() default  HttpMethodEnum.POST;
    String description()default "";
}
