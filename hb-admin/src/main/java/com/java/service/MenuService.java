package com.java.service;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Map<String,Object>> findHxMenus(Integer pageNum, Integer pageSize);
}
