package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@MapperScan(basePackages = "com.java.mapper")
@EnableCaching  //开启缓存
@EnableDiscoveryClient  //ribbon
@EnableScheduling //开启定时器
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
