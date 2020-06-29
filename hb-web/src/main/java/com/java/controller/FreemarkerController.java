package com.java.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Autowired
    private Configuration configuration;

    /*freemarker 页面测试
    * */
    @RequestMapping("/toFreemarker")
    public ModelAndView toFml(){
        //public表示公开直接访问的
        //封装跳转路径
        ModelAndView mv = new ModelAndView("fm1");
        mv.addObject("uName","test");
        Map<String,Object> dataMap = new HashMap<>();
        List<String> imgUrlList = Arrays.asList("http://1.jpg","http://2.jpg");
        dataMap.put("imgUrlList",imgUrlList);
        dataMap.put("title","iphone 8");
        dataMap.put("subtitle","1折甩卖!");
        dataMap.put("date",new Date());
        dataMap.put("price",3.1415926F);
        //
        Map<String,Object> aMap = new HashMap<>();
        aMap.put("a",10);
        aMap.put("b",8);
        aMap.put("c",12);
        dataMap.put("aMap",aMap);
        //装数据方式
        mv.addAllObjects(dataMap);
        return mv;
    }

    /*根据ftl生成html文件*/
    @RequestMapping("/getProductHtmlByFtl/{product}")
    public @ResponseBody String getProductHtmlByFtl(@PathVariable(name = "product") String product){
        try {
            //1、获取代表flt文件的模板对象
            Template template = configuration.getTemplate("Product.ftl");
            File file = new File("E:\\freemarkerHtml\\"+product+".html");
            FileWriter fw = new FileWriter(file);
            //3、从数据库中查询产品的具体信息
            Map<String,Object> dataMap = new HashMap<>();
            List<String> imgUrlList = Arrays.asList("http://192.168.25.133/group1/M00/00/04/wKgZhVxozjWACYeZAACmK0sntbI152.jpg","http://192.168.25.133/group1/M00/00/04/wKgZhVxp_zmAD0HzAADtVrCvlSc088.jpg","http://192.168.25.133/group1/M00/00/04/wKgZhVxqI2GAO_0nAABn0cwGIro754.jpg");
            dataMap.put("imgUrlList",imgUrlList);
            dataMap.put("title","iphone 8");
            dataMap.put("subTitle","1折甩卖!");
            dataMap.put("price",2999.9F);
            dataMap.put("type","运行内存2G 存储内存128G");
            dataMap.put("color","玫瑰金");
            //4、通过freemaker生成数据
            template.process(dataMap,fw);
            //5、关流
            fw.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
