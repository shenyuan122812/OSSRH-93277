package org.jtar.request;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jtar.annotations.HttpBase;
import org.jtar.annotations.HttpMethodEnum;
import org.jtar.assertion.AssertUtil;
import org.jtar.assertion.AssertionDto;
import org.jtar.testcaseTools.TestCaseDto;
import org.jtar.yaml.FileType;
import org.jtar.yaml.YamlReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


/**
 * author Allen.Shen
 * createTime 2023/5/28 2:01
 * description
 */
@Slf4j
public class RestAssuredTool {
     private  final  static  YamlReader APPLICATION = new YamlReader(FileType.APPLICATION_YAML);
     private  final  static  String HOST =APPLICATION.getValueByKey("project.host").toString();

    public  static  void  init(TestCaseDto testCaseDto){
        Boolean judgeNullResult = judgeNull(testCaseDto);
        if (judgeNullResult){
         log.info("---------------------数据符合要求开始进入接口请求--------------");
            Map<String, String> headers = apiObjectToMap(testCaseDto.getHeaders());
            Map<String, String> data = apiObjectToMap(testCaseDto.getData());
            if (StringUtils.isBlank(HOST)){
           log.error("project.host不能为空");
       }    Allure.addAttachment("测试用例标题",testCaseDto.getDetail());
            String responseResult = request(HOST+ testCaseDto.getUrl(), testCaseDto.getMethod(),testCaseDto.getRequestType(), headers,data );
            AssertUtil.init(responseResult,objectToList(testCaseDto.getAssertion()));
        }else {
            log.error("数据不符合请求");
        }
    }
    private  static  Map<String,String>  apiObjectToMap(Object o){
        return JSON.parseObject(JSON.toJSONString(o), HashMap.class);
    }
    private  static  List<AssertionDto>  objectToList(Object o){

        JSONArray list = JSONArray.from(o);
        return   list.stream().map(x->{
            AssertionDto assertionDto = new AssertionDto();
            JSONObject jsonObject = JSONObject.from(x);
            assertionDto.setAssertionField(jsonObject.get("assertionField").toString());
            assertionDto.setJsonPath(jsonObject.get("jsonPath").toString());
            assertionDto.setType(jsonObject.get("type").toString());
            assertionDto.setValue(jsonObject.get("value"));
            assertionDto.setAssertionType(jsonObject.get("assertionType").toString());
            return assertionDto;
        }).collect(Collectors.toList());
    }
    //针对TestCaseDto每个字段进行判空和格式的校验
    private  static  Boolean  judgeNull(TestCaseDto testCaseDto){

        if (StringUtils.isBlank(testCaseDto.getHost())||!"${{host()}}".equals(testCaseDto.getHost())){
            log.error("host字段为空或者格式不对，请按照${{host()}}格式进行维护");
            return  false;
        }
        if (StringUtils.isBlank(testCaseDto.getUrl())){
            log.error("url不能为空");
            return  false;
        }
        if (StringUtils.isBlank(testCaseDto.getMethod())){
            log.error("method不能为空！");
            return  false;
        }
        if (ObjectUtils.isEmpty(testCaseDto.getHeaders())){
            log.error("headers不能为空");
            return false;
        }
        if (StringUtils.isBlank(testCaseDto.getRequestType())){
            log.error("requestType不能为空");
            return  false;
        }
        if(ObjectUtils.isEmpty(testCaseDto.getData())){
            log.error("data不能为空");
            return  false;
        }
        if (StringUtils.isBlank(testCaseDto.getDependenceCaseData())){
            log.error("dependenceCaseData不能为空");
        }
        return  true;
    }
    public static String request(String url, String method, Map<String, String> headers, Object object) {
        log.info("请求路径===>"+url);
        log.info("请求方法===>"+method);
        RequestSpecification requestSpecification = given();
        requestSpecification = body(object, requestSpecification);

        requestSpecification = requestSpecification.headers(headers);
        Response response = requestSpecification.request(method, url);
        log.info("http响应码===> {},接口返回体====> {}", response.statusCode(), response.asString());

        return response.asString();
    }

    public static String request(String url, String method, String requestType,Map<String, String> headers, Map<String,String> body) {
        Allure.addAttachment("请求路径 ",url);
        Allure.addAttachment("请求方法 ",method);
        Allure.addAttachment("请求参数 ", body.toString());
        Allure.addAttachment("请求参数类型 ",requestType);
        log.info("请求路径===> "+url);
        log.info("请求方法===> "+method);
        log.info("请求参数===> "+body);
        log.info("请求参数类型===> "+requestType);
        log.info("请求头==> "+headers);
        RequestSpecification requestSpecification = given();
        if ("params".equals(requestType)){
            requestSpecification = requestSpecification.params(body);
        }else if ("data".equals(requestType)){
            requestSpecification = requestSpecification.formParams(body);
        }
        requestSpecification= requestSpecification.contentType(ContentType.URLENC);
        Response response = requestSpecification.request(method, url);
        Map<String, String> cookies = response.cookies();
        Allure.addAttachment("cookies ",cookies.toString());
        Allure.addAttachment("http响应码 ", String.valueOf(response.statusCode()));
        Allure.addAttachment("接口返回体 ",response.asString());
        log.info("-----cookies-----"+cookies);
        log.info("-----请求路径------ "+url);
        log.info("http响应码==> "+response.statusCode());
        log.info("接口返回体==> "+response.asString());
        return response.asString();
    }
    private static Map<String, String> getHeaders(HttpMethodEnum method, String url) {
        String xRequestId = "xRequestId-AutoTestCase-" + System.currentTimeMillis();
        log.info("xRequestId=>{}", xRequestId);
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
        Map<String, String> headers = new HashMap<>();
        headers.put("xRequestId", xRequestId);

        String ContentType = "application/json";
        if (url.contains("orchestration")) {
            ContentType = "text/plain;charset=utf-8";

            headers.put("X-Zenlayer-User-Id", "1");
        }
        headers.put("Content-type", ContentType);

        headers.put("Accept", "application/json");

        headers.put("Date", timeStamp);


        return headers;
    }

    public static Map<String, Object> getHttp() {
        Map<String, Object> map = new HashMap<>(16);

        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[0];

        Class<?> clazz = null;
        Method[] methods = null;
        try {
            clazz = Class.forName(stackTraceElement.getClassName());
            methods = clazz.getMethods();
        } catch (Exception ignored) {
        }
        HttpMethodEnum httpMethod = null;
        String description = "";
        assert methods != null;
        for (Method method : methods) {
            if (method.getName().equals(stackTraceElement.getMethodName())) {
                httpMethod = method.getAnnotation(HttpBase.class).httpMethod();
                description = method.getAnnotation(HttpBase.class).description();
            }
        }
        map.put("httpMethod", httpMethod);
        map.put("description", description);
        return map;
    }


    private static String paramsTransUrl(Map<String, Object> paraMap) {
        List<String> keys = new ArrayList<>(paraMap.keySet());
        Collections.sort(keys);
        String prestr = "?";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = paraMap.get(key);
            if (i == keys.size() - 1) {   //拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    public static RequestSpecification uploadFile(String url, RequestSpecification requestSpecification, Map<String, String> headers) {
        if (url.contains("/loa/upload/")) {
            headers.remove("Content-type");
            headers.remove("Accept");
            File file = new File(System.currentTimeMillis() + "");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            requestSpecification = requestSpecification.multiPart("file", file);
        }
        return requestSpecification;
    }

    public static RequestSpecification body(Object object, RequestSpecification requestSpecification) {
        if (object != null) {
            String body = "";
            if (object.getClass().equals(ArrayList.class)) {
                body = JSONUtil.parseArray(object).toString();
            } else if (object.getClass().equals(String.class)) {
                body = object.toString();
            } else {
                body = JSONUtil.parseObj(object).toString();
            }
            requestSpecification = requestSpecification.body(body);
            log.info("body====>{}", body);
        }
        return requestSpecification;
    }

    public static String url( String url, Map<String, Object> params, Map<String, Object> httpMap) {
        HttpMethodEnum method = (HttpMethodEnum) httpMap.get("httpMethod");
        String description = (String) httpMap.get("description");


        if (params == null) {
            params = new HashMap<>();
        }

        url = url + paramsTransUrl(params);
        log.info("url====>{}  method====>{} description=====>{}", url, method.toString(), description);

        return url;
    }



}
