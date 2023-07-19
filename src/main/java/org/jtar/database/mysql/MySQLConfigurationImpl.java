package org.jtar.database.mysql;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jtar.annotations.DBEnum;
import org.jtar.database.DataBaseDTO;
import org.jtar.database.DbConfiguration;

/**
 * author Allen.Shen
 * createTime 2023/6/12 5:11
 * description 检查数据库配置
 */


@Slf4j
public class MySQLConfigurationImpl implements DbConfiguration {
    private DataBaseDTO dataBaseDTO;

    public MySQLConfigurationImpl(DataBaseDTO dataBaseDTO) {
        this.dataBaseDTO = dataBaseDTO;
    }

    public MySQLConfigurationImpl() {

    }

    /**
     * 检查数据库配置 例如常用的mysql
     */
    public void check() {
        if (ObjectUtils.isNotEmpty(dataBaseDTO) && DBEnum.MYSQL.equals(dataBaseDTO.getDbType())) {
            log.info("使用mysql");
        } else {
            log.info("不使用mysql");
        }

    }

    /**
     * 调用redis连接工具
     */



}
