package com.siwen.seckilling.service;

/**
 * @Author siwen
 * @Date 2021/5/12 17:09
 * @Description
 **/
public interface RedisService<T> {



    boolean exists(String prefix, String key);


}
