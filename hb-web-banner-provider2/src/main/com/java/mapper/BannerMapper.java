package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BannerMapper {
    @Select("SELECT * FROM web_banner ORDER BY updateTime DESC LIMIT 6")
    List<Map<String,Object>> selectBanners();
}
