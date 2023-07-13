package org.jtar.annotations;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JtarValue {

    String value();
}
