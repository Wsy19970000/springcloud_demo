package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuMapper {

    /**
     * 查询横向导航栏
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType='1'")
    List<Map<String,Object>> selectHxMenus();

}
