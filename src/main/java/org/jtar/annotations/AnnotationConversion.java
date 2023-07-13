package org.jtar.annotations;

import lombok.Data;

/**
 * @author Allen.Shen
 * @createTime 2023/6/12 21:58
 * @description 注解转换类
 */
@Data
public class AnnotationConversion {

    private Class<?> clazz;

    private String annotationValue;
}
