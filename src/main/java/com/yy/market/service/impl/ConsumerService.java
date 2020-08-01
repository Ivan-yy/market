package com.yy.market.service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
public class ConsumerService {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("activemq-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            MapMessage receive = (MapMessage)consumer.receive(1000L);
            if(receive != null){
                System.out.println("consumer 接收消息："+receive.toString());
                receive.acknowledge();
            }else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();
    }
}
