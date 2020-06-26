package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner_consumer")
public class BannerController {
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/getBannersByConsumer")
    public @ResponseBody
    List<Map<String,Object>> getBannersByConsumer(){
        //服务名:application.yml里面的name  返回数据类型
        return restTemplate.getForObject("http://banner-provider/banner/getWebBanners",List.class);
    }
}
