package org.jtar.test;


import io.qameta.allure.*;
import org.jtar.annotations.AnnotationUtils;
import org.jtar.annotations.BindContent;
import org.jtar.request.RestAssuredTool;
import org.jtar.testcaseTools.TestCaseDto;
import org.jtar.testcaseTools.TestCaseGenerator;
import org.testng.annotations.Test;


import java.util.Map;


/**
 * author Allen.Shen
 * createTime 2023/6/11 16:32
 * description
 */
@Epic("登录Epic")
@Feature("登录Feature")
@BindContent(dataFileName = "login.yaml")
public class TestAA  {


    @Test
    @Story("登录Story")
    @Description("登录")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "百度登录页面",name = "百度登录页面",url = "https://www.baidu.com")
    @Issue("一个bug")
    @TmsLink("https://www.baidu.com")
    @BindContent(caseId = "login_01",uploadFileName = "test.xlsx")
    public  void  login_01(){

        Map<String, String> dataMap = AnnotationUtils.getBindContent(TestAA.class, "login_01");
        TestCaseDto testCaseDto = TestCaseGenerator.init(dataMap);
        RestAssuredTool.init(testCaseDto);

    }

}
