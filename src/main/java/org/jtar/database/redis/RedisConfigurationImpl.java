package org.jtar.database.redis;

import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jtar.annotations.DBEnum;
import org.jtar.database.DataBaseDTO;
import org.jtar.database.DbConfiguration;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisConfigurationImpl implements DbConfiguration {

    private DataBaseDTO dataBaseDTO;
    public   RedisConfigurationImpl(){

    }

    public  RedisConfigurationImpl(DataBaseDTO dataBaseDTO){
        this.dataBaseDTO =dataBaseDTO;
    }
    /**
     * 校验redis
     */
    @Override
    public void check( ) {
        if (ObjectUtils.isNotEmpty(dataBaseDTO) &&DBEnum.REDIS.equals(dataBaseDTO.getDbType())){
            log.info("使用redis");
            Jedis jedis = JedisUtil.cliSingle(dataBaseDTO.getHost(), dataBaseDTO.getPort(), dataBaseDTO.getPasswd());
        }else {
            log.info("不使用redis");
        }
    }
}
