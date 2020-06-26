package com.java.controller;

import com.github.pagehelper.PageInfo;
import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("/toBanner")
    public String toBanner(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               Model model){
        List<Map<String, Object>> bannerList = bannerService.findBanners(pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(bannerList);
        model.addAttribute("pageInfo",pageInfo);
        return "/bannerList.jsp";
    }

    @RequestMapping("/addBanner")
    @ResponseBody
    public boolean addBanner(@RequestParam Map<String,Object> paramMap){
        System.out.println("paramMap="+paramMap);
        boolean flag = bannerService.saveBanner(paramMap);
        return flag;
    }
}
