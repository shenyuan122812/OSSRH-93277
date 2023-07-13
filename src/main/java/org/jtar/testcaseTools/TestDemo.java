//package org.jtar.testcaseTools;
//
//
//import io.qameta.allure.*;
//import lombok.extern.slf4j.Slf4j;
//
//import org.jtar.assertion.AssertUtil;
//import org.jtar.utils.file_tool.BindCase;
//import org.jtar.utils.file_tool.GetTestCaseData;
//import org.jtar.utils.handler.TestDataHandler;
//import org.jtar.utils.request_tool.RequestControl;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//import org.jtar.utils.file_tool.BindFile;
//
//import org.jtar.utils.file_tool.entity.dto.NewApiDTO;
//import org.jtar.log_tool.CustomListener;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Allen.Shen
// * @createTime 2023/5/28 2:52
// * @description
// */
//@Listeners(CustomListener.class)
//@Epic("开发平台接口")
//@Feature("收藏模块")
//@Slf4j
//@BindFile(yamlFileName = "login.yaml")
//
//public class TestDemo {
//
//    @BindCase(caseId ={"login_01"})
//    @Test(description = "login_01")
//    @Story("login_01")
//    @Description("描述")
//    @Step("步骤")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("测试人员")
//    @Issue("Issue")
//    @Link(name = "接口地址",url = "https://wanandroid.com/blog/show/2",value = "测试")
//    public void login_01(){
//        Method login_01 =null;
//        try {
//            login_01 = TestDemo.class.getMethod("login_01");
//
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        String[] caseIds = login_01.getAnnotation(BindCase.class).caseId();
//        List<String> strings = Arrays.asList(caseIds);
//        String fileName = getClass().getAnnotation(BindFile.class).yamlFileName();
//        List<NewApiDTO> newApiDTOS = GetTestCaseData.getApiValues(fileName,strings);
//        newApiDTOS = TestDataHandler.preHandler(newApiDTOS);
//        newApiDTOS = RequestControl.handle(newApiDTOS);
//        AssertUtil.handle(newApiDTOS);
//        Allure.addAttachment("测试","text","测试一下",".log");
//    }
//
//    @BindCase(caseId ={"login_02"})
//    @Test(description = "login_02")
//    @Story("login_02")
//    public void login_02(){
//        Method login_02 =null;
//        try {
//             login_02 = TestDemo.class.getMethod("login_02");
//
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        String[] strings = login_02.getAnnotation(BindCase.class).caseId();
//        String fileName = getClass().getAnnotation(BindFile.class).yamlFileName();
//
//        List<String> collect = Arrays.stream(strings).sorted().collect(Collectors.toList());
//        List<NewApiDTO> newApiDTOS = GetTestCaseData.getApiValues(fileName,collect);
//        newApiDTOS = TestDataHandler.preHandler(newApiDTOS);
//        newApiDTOS = RequestControl.handle(newApiDTOS);
//        AssertUtil.handle(newApiDTOS);
//    }
//}
