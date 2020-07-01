package com.java.service;

import java.util.Map;


public interface SecKillService {
    void modifySeckKillInfo();

    void modifySeckKillProductStarted2Finish();

    /**
     * 根据秒杀id查询秒杀商品的详细信息
     * @param secId
     * @return
     */
    Map<String,Object> findtSeckillProductInfo(Long secId);
}
