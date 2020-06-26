package com.java.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NavService {
    List<Map<String,Object>> getHxNavs();
}
