package com.java.pojo;

import java.io.Serializable;
import java.util.List;

public class MyCar implements Serializable {


    private static final long serialVersionUID = -5L;
    private List<MyGood> goodList;

    public List<MyGood> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<MyGood> goodList) {
        this.goodList = goodList;
    }
}
