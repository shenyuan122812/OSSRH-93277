package org.jtar.testcaseTools;

import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.jtar.annotations.JtarRunType;
import org.jtar.yaml.FileType;
import org.jtar.yaml.YamlReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * author Allen.Shen
 * createTime 2023/6/12 0:17
 * description
 */
@Slf4j
@SuppressWarnings("all")
public class TestCaseGenerator {
    /**
     * 是否创建新的testcase
     * param createOrNot
     */
    public  static  void createOrNot(JtarRunType createSwitch){
      if (createSwitch.equals(JtarRunType.CAREATE_NEW_TESTCASE)){
          log.info("生成新的testcase");
         create();
      }else if (createSwitch.equals(JtarRunType.NOT_CAREATE_NEW_TESTCASE)){
          log.info("不创建新的testcase");
      }
    }

    /**
     * 根据testCasePath和achieveDataYamlPath创建testcase文件
     */
    public  static  void   create(){
        String achieveDataYamlPath = achieveDataYamlPath();
        URL yamlDataURL = TestCaseGenerator.class.getClassLoader().getResource(achieveDataYamlPath);
        assert yamlDataURL !=null;
        String yamlDataURLPath = yamlDataURL.getPath();
        yamlDataURLPath= yamlDataURLPath.substring(1,yamlDataURLPath.length());
        yamlDataURLPath= yamlDataURLPath.replace("/","\\");
        List<String> dataYamlPaths = findFiles(yamlDataURLPath);
        String finalYamlDataURLPath = yamlDataURLPath;
        List<String> newDataYamlPaths = dataYamlPaths.stream().map(dataYamlPath -> {
            if (dataYamlPath.startsWith(finalYamlDataURLPath)){
                dataYamlPath=dataYamlPath.replace(finalYamlDataURLPath,"");
                return  dataYamlPath;
            }else {
                return  null;
            }
        }).collect(Collectors.toList());
        String testCasePath = achieveTestCasePath();
        String property = System.getProperty("user.dir");
        assert property != null;

        testCasePath = property+"\\src\\main\\java\\"+testCasePath;
        List<String> strings = pathConversion(newDataYamlPaths, testCasePath);
        System.out.println("测试目录"+strings);

        strings.forEach(x->{
            createFiles(x);
        });

    }
    public  static  TestCaseDto  init(Map<String,String> dataMap){
        String dataFileName = dataMap.get("dataFileName");
        String uploadFileName = dataMap.get("uploadFileName");
        String casedId = dataMap.get("casedId");
        if (StringUtils.isBlank(dataFileName)){
            log.error("dataFileName不能为空");
        }else {
            if (StringUtils.isBlank(casedId)){
                log.error("casedId不能为空");
            }else {
                if (StringUtils.isBlank(uploadFileName)){
                    log.info("uploadFileName为空,该接口{}无需上传文件",casedId);
                }else {
                    String dataFilePathMatch = dataFilePathMatch(dataFileName);
                    YamlReader yamlReader = new YamlReader(FileType.DATA_YAML, dataFilePathMatch);
                    Object apiData = yamlReader.getValueByKey(casedId);
                    TestCaseDto testCaseDto = apiObjectToDto(apiData);
                    return  testCaseDto;
                }

            }
        }
      return null;

    }

    /**
     * 进一步解析接口内容
     * @param o
     */
    private  static  TestCaseDto  apiObjectToDto(Object o){
        return JSON.parseObject(JSON.toJSONString(o), TestCaseDto.class);
    }

    /**
     * 获取目标文件的实际完整地址
     * @param destinationFilePath
     * @return
     */
    private  static  String  dataFilePathMatch(String destinationFilePath){
        String absolutePath = new ClassPathResource("data").getAbsolutePath();
        ArrayList<String> list= new ArrayList<>();
        List<String> dataFilePaths = findFiles(absolutePath);
        dataFilePaths.forEach(x->{
            if (x.contains(destinationFilePath)){
                list.add(x);
            }
        });
        return list.get(0);
    }

    /**
     * 获取testcase文件夹的路径
     * return
     */
    public  static  String  achieveTestCasePath(){
        Object testcase = new YamlReader(FileType.APPLICATION_YAML).getValueByKey("project.paths.testcase");
        if (testcase !=null){
            return  testcase.toString();
        }else {
            log.error("请在application.yaml文件中维护testcase文件夹的路径！");
            return  null;
        }
    }

    /**
     * 获取data文件夹的路径
     * return
     */
    public  static  String  achieveDataYamlPath(){
        Object data = new YamlReader(FileType.APPLICATION_YAML).getValueByKey("project.paths.data");
        if (data!=null){
            return  data.toString();
        }
         log.error("请在application.yaml文件中维护data文件夹的路径！");
        return  null;
    }

    /**
     * 创建文件和文件夹
     * param filePath
     */
    public  static  void  createFiles(String  filePath){
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()){
            //创建文件夹
            if (parentFile.mkdirs()){
                log.info("创建{" + parentFile + "}目录成功");
            } else {
               log.error("创建{" + parentFile + "}目录失败");
            }
        }
        File file2 = new File(filePath);
        if (file2.exists()){
            log.error("{"+file2.getName()+"}该文件已存在,不能重复创建");
        } else {
            //创建文件
            try {
                if (file2.createNewFile()){
                    log.info("{"+file2.getName()+"}文件创建成功");
                    //写入内容
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    String property = System.getProperty("user.dir");
                     String replace=file2.getParent().replace(property+"", "").replace("\\",".").replace(".src.main.java.","");
                    Object valueByKey = new YamlReader(FileType.DATA_YAML, file2.getPath()).getValueByKey("caseCommon");
                    System.out.println(valueByKey);
                    String content =TestCaseTemplate.getTemplate(replace,file2.getName().replace(".java",""),null);
                    writer.write(content);
                    writer.close();
                } else {
                    log.error("{"+file2.getName()+"}文件创建失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {

        String s = achieveDataYamlPath();
        System.out.println(s);
    }

    /**
     * 将yaml文件路径转换为测试代码路径
     */
    public   static List<String> pathConversion(List<String> yamlDataPathList,String testCasePath){
        List<String> testCasePathList = yamlDataPathList.stream().map(x -> {
                x= x.replace("\\","/");
                String newTestCasePath = null;
                if (x.endsWith(".yaml")){
                    x = x.replace(".yaml", ".java");
                }
                if (x.endsWith(".yml")){
                    x = x.replace(".yml", ".java");
                }
                newTestCasePath= testCasePath+ x;
                File file = new File(newTestCasePath);
                String fileName = file.getName();
                String capitalization = fileName.substring(0, 1).toUpperCase();
                String  newFileName ="Test"+capitalization+ fileName.substring(1,fileName.length());
                newTestCasePath = file.getParent().concat("\\").concat(newFileName);
            return  newTestCasePath;
        }).collect(Collectors.toList());
        return  testCasePathList;
    }

    /**
     * 获取所有文件路径
     * param filePath
     * return
     */
    public   static List<String> findFiles(String filePath){
        File file = new File(filePath);
        File[] files = file.listFiles();
        ArrayList<String> filePathList =new ArrayList<>();
        if (files != null && files.length>0){
            for (File fs: files) {
                if (fs.isDirectory()){
                    List<String> recursion = recursion(fs);
                    filePathList.addAll(recursion);
                }else if (fs.isFile()){
                    filePathList.add(file.getPath());
                }
            }

        }

        return  filePathList;

    }

    /**
     * 文件夹递归
     * @param directory
     * @return
     */
    private  static  List<String>  recursion(File  directory){
        ArrayList<String> filePathList = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files !=null && files.length>0){
            for (File file :files){
                if (file.isDirectory()){
                    recursion(file);
                }else if (file.isFile()){
                    filePathList.add(file.getPath());
                }
            }
        }

        return filePathList;
    }
}
