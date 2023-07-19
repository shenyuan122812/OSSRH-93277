package org.jtar.annotations;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class AnnotationUtils {
//    /**
//     * 获取类上的BindContent注解内容dataFileName
//     * param clazz
//     * return
//     */
//    public  static  String  getBindContent(Class<?> clazz){
//
//        String dataFileName = clazz.getAnnotation(BindContent.class).dataFileName();
//        if (StringUtils.isBlank(dataFileName)){
//            log.error("类"+clazz+"上的dataFileName为空！");
//            return  null;
//
//        }else {
//            return  dataFileName;
//        }
//
//    }

    /**
     * 获取方法名上面注解 内容包括casedId，dataFileName
     * param clazz
     * param name
     * return map
     */
    public  static Map<String, String> getBindContent(Class<?> clazz, String name)  {
        Map<String,String> map = new HashMap<>();
        String dataFileName = clazz.getAnnotation(BindContent.class).dataFileName();
        if (StringUtils.isBlank(dataFileName)){
            log.error("类"+clazz+"上的dataFileName为空！");
        }else {
            map.put("dataFileName",dataFileName);
        }
        Method method = null;
        try {
            method = clazz.getMethod(name, null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        String casedId = method.getAnnotation(BindContent.class).caseId();
        String uploadFileName = method.getAnnotation(BindContent.class).uploadFileName();

        if (StringUtils.isNotBlank(casedId)){
            map.put("casedId",casedId);
            if (StringUtils.isNotBlank(uploadFileName)){
                map.put("uploadFileName",uploadFileName);
            }
        }
        return map;

    }
}
