package org.jtar;

import org.jtar.annotations.AnnotationConversion;
import org.jtar.annotations.JtarRunType;

import java.util.List;


/**
 * @author Allen.Shen
 * @createTime 2023/6/11 20:47
 * @description 配置文件扫描
 */

public class ConfigurationScan {
    /**
     * 检查数据库配置
     */
    public static void judgeDBSwitch(JtarRunType jtarRunType) {
        /**
         * 开启sql断言
         */
        if (jtarRunType.equals(JtarRunType.OPEN_SQL_ASSERTION)) {
            
        }
    }
}
