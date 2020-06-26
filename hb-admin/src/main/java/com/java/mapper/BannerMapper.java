package com.java.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BannerMapper {
    /**
     * 动态获取轮播图
     * @return
     */
    @Select("SELECT * FROM web_banner ORDER BY sort ASC")
    List<Map<String,Object>> selectBanners();

    /**
     * 添加轮播图信息
     * @param paramMap
     * @return
     */
    @Insert("INSERT INTO web_banner VALUES(NULL,#{imgUrl},#{href},#{remark},#{sort},NOW())")
    int insertBanner(Map<String,Object> paramMap);
}
