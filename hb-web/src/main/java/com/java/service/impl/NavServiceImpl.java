package com.java.service.impl;

import com.java.mapper.NavMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = false)
public class NavServiceImpl implements com.java.service.NavService {
    @Autowired
    private NavMapper navMapper;

    @Autowired
    private RedisTemplate redisTemplate;  //启用redis

    @Override
    public List<Map<String,Object>> getHxNavs(){
        //获取redis对象，从redis取出数据
        ValueOperations vop = redisTemplate.opsForValue();
        Object navs = vop.get("hxNavs");
        //是否存在redis
        if(navs == null ){
            //System.out.println("mysql");
            List<Map<String, Object>> hxNavs = navMapper.selectHxNavs();
            vop.set("hxNavs",hxNavs);
            //设置失效时间
            redisTemplate.expire("hxNavs",3, TimeUnit.HOURS);
            return hxNavs;
        }else{
            //System.out.println("redis");
            return (List<Map<String, Object>>)navs;
        }
    }


}
