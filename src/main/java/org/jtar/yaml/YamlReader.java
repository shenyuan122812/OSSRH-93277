package org.jtar.yaml;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * author Allen.Shen
 * createTime 2023/6/11 16:52
 * description
 */
@Slf4j
public class YamlReader {

    private static HashMap properties = new HashMap<>();


    public  YamlReader(FileType fileType){
        if (fileType.equals(FileType.APPLICATION_YAML)){
            Yaml yaml = new Yaml();
            try (InputStream in = YamlReader.class.getClassLoader().getResourceAsStream("application.yaml")) {
                properties = yaml.loadAs(in, HashMap.class);
            } catch (Exception e) {
                log.error("请在resources目录下维护application.yaml文件！", e);
            }
        }
    }

    public  YamlReader(FileType fileType,String path){
        if (fileType.equals(FileType.DATA_YAML)){
            File file = new File(path);
            Yaml yaml = new Yaml();
            try {
                properties= yaml.loadAs(new FileInputStream(file),HashMap.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


        }
    }
    /**
     * param key
     * return Object
     */
    public Object getValueByKey(String key) {
        String separator = ".";
        String[] separatorKeys = null;
        if (key.contains(separator)) {
            separatorKeys = key.split("\\.");
        } else {
            return properties.get(key);
        }
        Map finalValue = new HashMap<>();
        for (int i = 0; i < separatorKeys.length - 1; i++) {
            if (i == 0) {
                finalValue = (Map) properties.get(separatorKeys[i]);
                continue;
            }
            if (finalValue == null) {
                break;
            }
            finalValue = (Map) finalValue.get(separatorKeys[i]);
        }
        return finalValue == null ? null : finalValue.get(separatorKeys[separatorKeys.length - 1]);
    }

    public static void main(String[] args) {
//        project信息
        System.out.println(new YamlReader(FileType.DATA_YAML).getValueByKey("project.name"));


    }


}
