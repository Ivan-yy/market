package com.yy.market.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Slf4j
@Service
public class ProviderService {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    Queue queue;

    public void produceMsg(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("xxx","xxx");
        jmsMessagingTemplate.convertAndSend(queue, map);
        log.info("*** producer :"+map.toString());
    }

    /**
     * 3000ms发一次消息
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMsgSchedule(){
        Map<String,Object> map = new HashMap<>();
        map.put("123","new Object()");
        map.put("321",new Integer(2));
        jmsMessagingTemplate.convertAndSend(queue,map);
        log.info("*** producer :"+map.toString());
        jmsMessagingTemplate.convertAndSend(queue,new ArrayList<>());
        log.info("*** producer :"+"111");
    }

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("activemq-queue");
        MessageProducer producer = session.createProducer(queue);

        //Topic topic = session.createTopic("activemq-topic");
        //默认配置
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (int i = 0; i < 3; i++) {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("msg1","sos");
            producer.send(mapMessage);
            System.out.println("生产者生产消息: "+mapMessage.toString());
        }
        producer.close();
        //创建session时transacted设置为true时必须提交或者回滚
        session.commit();
        session.close();
        connection.close();
    }
}
