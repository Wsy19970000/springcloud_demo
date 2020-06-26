package com.java.controller;

import com.java.mapper.BannerMapper;
import com.java.service.BannerService;
import com.yuqing.common.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class PicUploadController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String,Object> uplaodFile(MultipartFile mf){
        HashMap<String, Object> map = new HashMap<>();
        try {
            FastDFSClient client = new FastDFSClient("classpath:resources/fdfs_client.conf"); //ctrl+alt+t捕获异常
            //获取文件名
            String name = mf.getOriginalFilename();
            //截取后缀名
            String extName = name.substring(name.lastIndexOf(".") + 1);
            //返回一个地址
            //client.uploadFile("文件字节码","文件后缀名");
            String url = client.uploadFile(mf.getBytes(), extName);
            url = "http://192.168.25.133/"+url;
            map.put("status",0);
            map.put("url",url);
            //boolean b = bannerService.saveBanner(map);
            //System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",1);
        } finally {
            return map;
        }
    }
}
