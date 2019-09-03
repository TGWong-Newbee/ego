package jedisDao.impl;


import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;


/**
 * Author : 王定
 * Date : 2019-08-19 10:59
 * Desscription : <描述>
 */
@Repository
public class JedisDaoImpl implements JedisDao {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public Boolean exist(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void expire(String key, int time) {
        jedisCluster.expire(key,time);
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }
}
