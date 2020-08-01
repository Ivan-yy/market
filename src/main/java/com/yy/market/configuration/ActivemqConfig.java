package com.yy.market.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@EnableJms
@Configuration
public class ActivemqConfig {
    @Value("${queue}")
    private String queueName;

    @Bean(name = "queue")
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }


}
