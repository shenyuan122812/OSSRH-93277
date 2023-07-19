package org.jtar.assertion;

import com.jayway.jsonpath.JsonPath;

/**
 * author Allen.Shen
 * createTime 2023/6/7 23:09
 * description  jsonPath提取器
 */

public class JsonPathUtil {

    public  static  String  extract(String resultBody,String jsonPath){
      return  JsonPath.read(resultBody,jsonPath).toString();
    }

}
