package com.java.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository

public interface ProductService {
    List<Map<String,Object>> findProducts();

    List<String> findProductImgByPID(Long productId);
}
