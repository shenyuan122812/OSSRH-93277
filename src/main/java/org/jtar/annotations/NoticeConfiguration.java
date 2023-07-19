package org.jtar.annotations;

import java.lang.annotation.*;

/**
 * @author Administrator
 * 通知配置类注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NoticeConfiguration {
    /**
     * 通知类型
     * return
     */
    NoticeType type() default NoticeType.DINGTALK;

}
