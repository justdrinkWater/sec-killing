package com.siwen.seckilling.service;

/**
 * @Author siwen
 * @Date 2021/5/12 17:09
 * @Description redis服务，包括查询，设置等服务
 **/
public interface RedisService<K,V> {

    /**
     *
     * @author siwen
     * @date 2021/5/12 21:33
     * @description 从redis中查询是否存在响应的key
     * @return 是否存在响应的key
     */
    boolean exists(String prefix, K key);


}
