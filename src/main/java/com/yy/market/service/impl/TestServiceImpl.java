package com.yy.market.service.impl;

import com.yy.market.service.ITestService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Service
public class TestServiceImpl implements ITestService {
    @Value("${queue}")
    private String queueName;

    public static void main(String[] args) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    }
}
