package com.java.controller;


import com.alibaba.fastjson.JSON;
import com.java.pojo.MyCar;
import com.java.pojo.MyGood;
import com.java.utils.Base64Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/buyCar")
public class MyBuyCarController {

    //数量跟产品Id，以及用户校验
    @ResponseBody
    @RequestMapping("/addCar")
    public boolean addBuyCar(String productId, Integer productNum, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String name = session.getAttribute("name").toString();
        //非登陆,先获取cookie购物车
        Cookie[] cookies = request.getCookies();
        if(name!=null && !"".equals(name)){
            //登陆

        }else{
            //不存在，创建购物车
            if(cookies==null || cookies.length==0){
                //创建购物车，将商品转换为MyGood
                MyCar myCar = new MyCar();
                MyGood myGood = new MyGood(productId,productNum);
                List<MyGood> myGoods = Arrays.asList(myGood);
                //添加进购物车
                myCar.setGoodList(myGoods);
                //j将购物车进行加密,加密前需转换为字符串
                String car = JSON.toJSONString(myCar);
                Base64Utils.getBASE64(car);

            }else{
                boolean flag = false;
                String cookieValue = null;
                //有cookie，看是否存在购物车，并且存在商品
                for (int i = 0; i <cookies.length ; i++) {
                    String cookieName = cookies[i].getName();
                    //找到购物车
                    if("buyCar".equals(cookieName)){
                         cookieValue = cookies[i].getValue();
                        if(cookieValue!=null && !"".equals(cookieValue)){
                            //有商品
                            flag = true;
                        }
                    }
                }
                if(flag){
                    //购物车有商品，添加,先解密购物车
                    String jsonBuycar = Base64Utils.getFromBASE64(cookieValue);
                    //json数据转换为购物车
                    MyCar myCar = JSON.parseObject(jsonBuycar, MyCar.class);
                    //遍历商品，看是否同一款商品，同一款，则添加，不同添加进来
                    List<MyGood> goodList = myCar.getGoodList();
                    for (int i = 0; i < goodList.size(); i++) {
                        MyGood good = goodList.get(i);
                        String productNum1 = good.getProductNum();
                        //同一商品，新增数量
                        if (productNum1.equals(productId)){
                            Integer num = good.getNum() + productNum;
                            good.setNum(num);
                            break;
                        }
                    }

                }else {
                    //购物车无商品，添加
                    //创建购物车，将商品转换为MyGood
                    MyCar myCar = new MyCar();
                    MyGood myGood = new MyGood(productId,productNum);
                    List<MyGood> myGoods = Arrays.asList(myGood);
                    //添加进购物车
                    myCar.setGoodList(myGoods);
                    //j将购物车进行加密,加密前需转换为字符串
                    String car = JSON.toJSONString(myCar);
                    String base64 = Base64Utils.getBASE64(car);
                    Cookie cookieTime = new Cookie("buyCar", base64);
                    //设置失效时间，格林时间，需多加8小时
                    cookieTime.setMaxAge(3600*32);
                    //回写给前台
                    response.addCookie(cookieTime);
                }
            }
        }
        return true;
    }
}
