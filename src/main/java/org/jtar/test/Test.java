package org.jtar.test;


import org.jtar.annotations.DBEnum;
import org.jtar.database.*;
import org.jtar.database.mysql.MySQLConfigurationImpl;
import org.jtar.database.redis.RedisConfigurationImpl;

/**
 * @author Allen.Shen
 * @createTime 2023/6/8 22:31
 * @description
 */

public class Test {

    public static void main(String[] args) {
        DataBaseDTO dataBaseDTO = new DataBaseDTO();
        dataBaseDTO.setDbType(DBEnum.REDIS);
        RedisConfigurationImpl redisConfiguration = new RedisConfigurationImpl(dataBaseDTO);
        redisConfiguration.check();
        DataBaseDTO dataBaseDTO1 = new DataBaseDTO();
        dataBaseDTO.setDbType(DBEnum.REDIS);
        MySQLConfigurationImpl mySQLConfiguration = new MySQLConfigurationImpl(dataBaseDTO1);
        mySQLConfiguration.check();
    }

}
