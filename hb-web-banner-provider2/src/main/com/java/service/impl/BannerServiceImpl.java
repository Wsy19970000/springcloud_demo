package com.java.service.impl;

import com.java.mapper.BannerMapper;
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
public class BannerServiceImpl implements com.java.service.BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Map<String,Object>> findBanners(){
        ValueOperations vop = redisTemplate.opsForValue();
        try {
            Object banner = vop.get("banner");
            if (banner == null){
                List<Map<String, Object>> maps = bannerMapper.selectBanners();
                vop.set("banner",maps);
                redisTemplate.expire("banner",5, TimeUnit.MINUTES);
                return maps;
            }else {
                return (List<Map<String, Object>>) banner;
            }
        } catch (Exception e) {
            List<Map<String, Object>> maps = bannerMapper.selectBanners();
            vop.set("banner",maps);
            redisTemplate.expire("banner",5, TimeUnit.MINUTES);
            return maps;
        }
    }
}
