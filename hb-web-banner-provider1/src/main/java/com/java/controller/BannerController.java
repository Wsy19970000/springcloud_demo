package com.java.controller;

import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getWebBanners")
    public @ResponseBody List<Map<String,Object>> getBanners(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        //return Arrays.asList(map);
        return bannerService.findBanners();
    }

}
