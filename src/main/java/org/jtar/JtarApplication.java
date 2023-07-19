package org.jtar;

import lombok.extern.slf4j.Slf4j;
import org.jtar.annotations.JtarRunApplication;
import org.jtar.annotations.JtarRunType;
import org.jtar.testcaseTools.TestCaseGenerator;

/**
 * author Allen.Shen
 * createTime 2023/6/11 20:29
 * description 框架启动类
 */
@Slf4j
public class JtarApplication {

    /**
     * 框架主启动方法
     * param primarySource
     * param args
     */
    public static void run(Class<?> primarySource, String[] args) {
        JtarRunApplication jtarRunApplicationAnnotation = primarySource.getAnnotation(JtarRunApplication.class);
        if (jtarRunApplicationAnnotation != null) {
            checkParameter(jtarRunApplicationAnnotation, args);
        } else {
            log.error("找不到主启动类！请在主启动类上添加注解@JtarRunApplication");
        }
    }

    /**
     * 检查run方法传入的参数 例如定义JtarRunApplication注解上的属性
     * param jtarRunApplication
     * param args
     */
    public static void checkParameter(JtarRunApplication jtarRunApplication, String... args) {
        checkJtarRunApplicationAttributes(jtarRunApplication);
    }

    /**
     * 检查JtarRunApplication注解上的参数
     * param jtarRunApplication
     */
    public static void checkJtarRunApplicationAttributes(JtarRunApplication jtarRunApplication) {

        JtarRunType generateNewTestCase = jtarRunApplication.generateNewTestCase();
        //判断是否创建新的测试用例代码
        if (generateNewTestCase.equals(JtarRunType.NOT_CAREATE_NEW_TESTCASE)) {
            TestCaseGenerator.createOrNot(generateNewTestCase);
        } else {
            TestCaseGenerator.createOrNot(generateNewTestCase);
        }
        //判断sql断言
        JtarRunType sqlAssertion = jtarRunApplication.sqlAssertion();
        if (sqlAssertion.equals(JtarRunType.OPEN_SQL_ASSERTION)) {
            log.info("开启sql断言");
            ConfigurationScan.judgeDBSwitch(sqlAssertion);
        } else {
            log.info("关闭sql断言");
        }
        //判断普通的断言
        JtarRunType ordinaryAssertion = jtarRunApplication.ordinaryAssertion();
        if (ordinaryAssertion.equals(JtarRunType.OPEN_ORDINARY_ASSERTION)) {
            log.info("开启常规断言");
        } else {
            log.info("关闭常规断言");
        }
       //判断通知类型
        JtarRunType notice = jtarRunApplication.notice();
        if (notice.equals(JtarRunType.DINGTALK)) {

            log.info("使用钉钉发送通知");
        } else if (notice.equals(JtarRunType.WECOM)) {

            log.info("使用企业微信发送通知");
        } else if (notice.equals(JtarRunType.EMAIL)) {

            log.info("使用邮件发送通知");
        } else {
            log.info("不发送通知");
        }

    }

}
