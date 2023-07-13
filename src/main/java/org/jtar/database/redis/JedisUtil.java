package org.jtar.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 *
 * @author jiangdoc
 *
 */
public class JedisUtil {
    public static void main(String[] args) {


    }

    /**
     * 单个连接
     *
     * @param host
     * @param port
     * @return
     */
    public static Jedis cli_single(String host, int port,String password) {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);
        return  jedis;
    }

    /**
     * 连接池
     *
     * @param host
     * @param port
     * @return
     */
    public static Jedis cli_pool(String host, int port) {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(10);
        // 最大连接空闲数
        config.setMaxIdle(2);
        JedisPool jedisPool = new JedisPool(config, host, port);

        try{

            return jedisPool.getResource();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
