package org.jtar.annotations;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JtarRunApplication {

    JtarRunType generateNewTestCase() default JtarRunType.NOT_CAREATE_NEW_TESTCASE;

    JtarRunType sqlAssertion() default JtarRunType.CLOSE_SQL_ASSERTION;

    JtarRunType ordinaryAssertion() default JtarRunType.CLOSE_ORDINARY_ASSERTION;

    JtarRunType notice() default JtarRunType.ClOSED_NOTICE;
}
