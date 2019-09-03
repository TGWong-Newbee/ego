package jedisDao;

/**
 * Author : 王定
 * Date : 2019-08-19 10:57
 * Desscription : <描述>
 */
public interface JedisDao {
    /**
     * 判断是否存在某个键值对
     * @param key
     * @return
     */
    public Boolean exist(String key);

    /**
     * 向redis中存储键值对
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 从redis中根据key 获取value
     * @param key
     * @return
     */
    public String get(String key);
    public void expire(String key,int time);
    void del(String key);
}
