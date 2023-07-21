package org.jtar.testcaseTools;

import java.util.List;

/**
 * author Allen.Shen
 * createTime 2023/6/12 5:26
 * description
 */

public class TestCaseTemplate {

    public static  String  getTemplate(String packageName, String clazzName, List<String> methodNameList){


   String template= "package "+packageName+";\n" +
            "\n" +
            "\n" +
            "import io.qameta.allure.*;\n" +
            "import org.jtar.annotations.AnnotationUtils;\n" +
            "import org.jtar.annotations.BindContent;\n" +
            "import org.jtar.request.RestAssuredTool;\n" +
            "import org.jtar.testcaseTools.TestCaseDto;\n" +
            "import org.jtar.testcaseTools.TestCaseGenerator;\n" +
            "import org.testng.annotations.Test;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "\n" +
            "/**\n" +
            " * @author Allen.Shen\n" +
            " * @createTime 2023/6/11 16:32\n" +
            " * @description\n" +
            " */\n" +
            "@Epic(\"登录Epic\")\n" +
            "@Feature(\"登录Feature\")\n" +
            "@BindContent(dataFileName = \"login.yaml\")\n" +
            "public class "+clazzName+"{\n" +
            "\n" +
            "\n" +
            "    @Test\n" +
            "    @Story(\"登录Story\")\n" +
            "    @Description(\"登录\")\n" +
            "    @Severity(SeverityLevel.NORMAL)\n" +
            "    @Link(value = \"百度登录页面\",name = \"百度登录页面\",url = \"https://www.baidu.com\")\n" +
            "    @Issue(\"一个bug\")\n" +
            "    @TmsLink(\"https://www.baidu.com\")\n" +
            "    @BindContent(caseId = \""+methodNameList+"\",uploadFileName = \"test.xlsx\")\n" +
            "    public  void  login_01(){\n" +
            "\n" +
            "        Map<String, String> dataMap = AnnotationUtils.getBindContent("+clazzName+".class, \"+"+methodNameList+"\");\n" +
            "        TestCaseDto testCaseDto = TestCaseGenerator.init(dataMap);\n" +
            "        RestAssuredTool.init(testCaseDto);\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "}";

   return  template;
    }
}
