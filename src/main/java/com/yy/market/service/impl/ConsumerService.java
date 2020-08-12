package com.yy.market.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("activemq-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            Message receive = consumer.receive(1000L);
            if(receive != null){
                System.out.println("consumer 接收消息："+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) +"  "+receive.toString());
                //transacted开启时无效 必须commit  只有transacted关闭时签收才会告诉broker已消费
//                receive.acknowledge();
            }else {
                break;
            }
        }
        consumer.close();
//        session.commit();
        session.close();
        connection.close();
    }
}
