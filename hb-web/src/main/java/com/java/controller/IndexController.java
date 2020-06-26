package com.java.controller;

import com.java.service.impl.NavServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
//@MapperScan(basePackages = "com.java.*")
public class IndexController {
    @Autowired
    private NavServiceImpl navService;

    @RequestMapping("/")
    public String getHxNavs(Model model){
        List<Map<String, Object>> hxNavs = navService.getHxNavs();
        model.addAttribute("hxNavs",hxNavs);
        return "Index.jsp";
    }

    //使用jmeter测试并发量
    @RequestMapping("/test")
    public @ResponseBody List<Map<String,Object>> test(){
        return navService.getHxNavs();
    }

}
