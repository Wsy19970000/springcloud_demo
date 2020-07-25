package com.java.pojo;


import java.io.Serializable;

public class MyGood implements Serializable {

    private static final long serialVersionUID = -99L;
    private String productNum;//商品编号
    private Integer num;//商品购买数量

    @Override
    public String toString() {
        return "MyGood{" +
                "productNum='" + productNum + '\'' +
                ", num=" + num +
                '}';
    }

    public MyGood(String productNum, Integer num) {
        this.productNum = productNum;
        this.num = num;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
