package com.java.service;

/**
 * description：秒杀模块往RabbitMQ中的队列中发送数据
 */
public interface OrderService {

    void sendData2MQ(Long secId, String uName);

}
