package org.jtar.assertion;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * author Allen.Shen
 * createTime 2023/6/8 11:49
 * description
 */
@Slf4j
public class AssertUtil {

    public static  void  init(String response, List<AssertionDto> list){
        System.out.println("response------"+response);
        System.out.println("list--------"+list);
    }
    /**
     * 等于
     * @param resultBodyValue
     * @param assertionValue
     */
    private  static  void  eq(String resultBodyValue,String assertionValue){
        if (resultBodyValue.equals(assertionValue)){
            log.info("断言结果====> 断言成功！ 预期值---->{},实际值---->{}",assertionValue,resultBodyValue);
            String content = " 断言成功！ 预期值---->{"+assertionValue+"},实际值---->{"+resultBodyValue+"}";
            Allure.addAttachment("断言结果",content);
        }else {
            log.info("断言结果====> 断言失败！ 预期值---->{},实际值---->{}",assertionValue,resultBodyValue);
            String content = " 断言失败！ 预期值---->{"+assertionValue+"},实际值---->{"+resultBodyValue+"}";
            Allure.addAttachment("断言结果",content);
        }

    }

    private  static  void  notEq(String resultBodyValue,String assertionValue){

        if (!resultBodyValue.equals(assertionValue)){
            log.info("断言结果====> 断言成功！ 预期值---->{},实际值---->{}",assertionValue,resultBodyValue);
            String content = " 断言成功！ 预期值---->{"+assertionValue+"},实际值---->{"+resultBodyValue+"}";
            Allure.addAttachment("断言结果",content);
        }else {
            log.info("断言结果====> 断言失败！ 预期值---->{},实际值---->{}",assertionValue,resultBodyValue);
            String content = " 断言失败！ 预期值---->{"+assertionValue+"},实际值---->{"+resultBodyValue+"}";
            Allure.addAttachment("断言结果",content);
        }

    }
//    public static void handle(List<NewApiDTO> newApiDTOS) {
//        newApiDTOS.forEach(newApiDTO -> {
//            List<AssertionDTO> assertions = newApiDTO.getAssertion();
//            assertions.forEach(assertionDTO -> {
//                if ("==".equals(assertionDTO.getType())){
//                    Allure.addAttachment("断言字段",assertionDTO.getAssertionField());
//                    Allure.addAttachment("断言类型",assertionDTO.getType());
//                    String extract = JsonPathUtil.extract(newApiDTO.getResultBody(), assertionDTO.getJsonPath());
//                    eq(extract,assertionDTO.getValue());
//                }
//            });
//        });
//    }
}
