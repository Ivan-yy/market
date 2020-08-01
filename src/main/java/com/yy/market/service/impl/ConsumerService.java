package com.yy.market.service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.UUID;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Component
public class ConsumerService {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    Queue queue;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue, UUID.randomUUID().toString());
    }

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("activemq-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            Message receive = consumer.receive(1000L);
            if(receive != null){
                System.out.println("consumer 接收消息："+receive.toString());
                receive.acknowledge();
            }else {
                break;
            }
        }
        consumer.close();
        session.commit();
        session.close();
        connection.close();
    }
}
