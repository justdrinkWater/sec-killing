package com.siwen.seckilling.service;

/**
 * @Author siwen
 * @Date 2021/5/12 17:09
 * @Description redis服务，包括查询，设置等服务
 **/
public interface RedisService<K, V> {

    /**
     * @author siwen
     * @date 2021/5/13 09:35
     * @description 设置值
     */
    boolean set(K key, V value);

    /**
     * @return boolean
     * @author siwen
     * @date 2021/5/13 09:50
     * @description hash 设置值
     */
    Long hSet(K mapKey, K key, V value);

    /**
     * @author siwen
     * @date 2021/5/13 09:46
     * @description 设置值，包括过期时间
     */
    boolean set(K key, V value, int expire);

    /**
     * @return 是否存在响应的key
     * @author siwen
     * @date 2021/5/12 21:33
     * @description 从redis中查询是否存在响应的key
     */
    boolean exists(K key);

    /**
     * @author siwen
     * @date 2021/5/13 09:54
     * @description 判断hash里面是否存在响应的key
     */
    boolean hExists(K mapKey, K key);


    /**
     * @author siwen
     * @date 2021/5/13 14:07
     * @description 获取数据
     */
    String get(K key);

    /**
     * @author siwen
     * @date 2021/5/13 14:06
     * @description 获取数据，hash
     */
    String hGet(K mapKey, K key);

    /**
     * @return boolean
     * @author siwen
     * @date 2021/5/13 15:25
     * @description 增加值，+1
     */
    Long hIncr(K mapKey, K key);

    /**
     * @author siwen
     * @date 2021/5/14 13:29
     * @description 减少值 -1
     */
    boolean decr(K key);

    /**
     * @return boolean
     * @author siwen
     * @date 2021/5/13 15:25
     * @description hash减少值，-1
     */
    long hDecr(K mapKey, K key);

    /**
     * @author siwen
     * @date 2021/5/13 15:48
     * @description 删除 hash
     */
    boolean hDel(K mapKey, K key);

    /**
     * @author siwen
     * @date 2021/5/14 14:28
     * @description 执行lua脚本
     */
    Object execLua(String script, String... params);


}
