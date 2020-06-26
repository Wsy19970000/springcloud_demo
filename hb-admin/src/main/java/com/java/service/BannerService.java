package com.java.service;

import java.util.List;
import java.util.Map;

public interface BannerService {
    List<Map<String,Object>> findBanners(Integer pageNum, Integer pageSize);

    boolean saveBanner(Map<String, Object> paramMap);
}
