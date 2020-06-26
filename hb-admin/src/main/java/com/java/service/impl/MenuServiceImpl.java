package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = false)
public class MenuServiceImpl implements com.java.service.MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Map<String,Object>> findHxMenus(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return menuMapper.selectHxMenus();
    }

}
