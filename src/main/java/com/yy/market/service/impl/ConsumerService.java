package com.yy.market.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Slf4j
@Component
public class ConsumerService {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @JmsListener(destination = "${queue}")
    public void receive(Message message)throws Exception{
        log.info("*** consumer :"+message.toString());
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
