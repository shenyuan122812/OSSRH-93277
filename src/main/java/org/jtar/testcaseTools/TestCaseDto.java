package org.jtar.testcaseTools;

import lombok.Data;

/**
 * 接口信息
 */
@Data
public class TestCaseDto {
 //域名
 private  String host;
 //接口地址
 private  String url ;
//请求方式
 private  String method;
 //请求头
 private  Object headers;
 //请求数据类型
 private  String requestType;
 //是否运行
 private  Boolean isRun;
 //请求的数据
 private  Object data;
 //是否数据依赖
 private  Boolean dependenceCase;
 //依赖的数据
 private  String  dependenceCaseData;
 //断言
 private  Object assertion;
 //sql
 private  Object sql;

 private  String detail;

}
