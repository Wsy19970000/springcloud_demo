package com.java.task;

import com.java.service.ProductService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ProductTask {
    @Autowired
    private ProductService productService ;

    @Autowired
    private Configuration config;


    @Scheduled(cron = "* * 12 * * *")  //秒分时 日月年
    public void getHtmlByFtl(){
        try {
            System.out.println("---------");
            List<Map<String, Object>> products = productService.findProducts();
            for (int i = 0; products!=null &&i <products.size() ; i++) {
                //取出某个商品的编号
                Map<String, Object> dataMap = products.get(i);
                String productNum = (String) dataMap.get("productNum");
                //取出商品主键
                Long productId = (Long) dataMap.get("id");
                List<String> imgUrlList  = productService.findProductImgByPID(productId);
                dataMap.put("imgUrlList",imgUrlList);
                //2、获取代表flt文件的模板对象
                Template template = config.getTemplate("Product.ftl");
                //3、静态文件保存的位置
                File file = new File("E:\\freemarkerHtml\\"+productNum+".html");
                FileWriter fw = new FileWriter(file);
                //4、通过freemaker生成数据
                template.process(dataMap,fw);
                //5、关流
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
