package com.yy.market.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("activemq-queue");
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);

        //Topic topic = session.createTopic("activemq-topic");
        //默认配置
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (int i = 0; i < 3; i++) {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setJMSMessageID(UUID.randomUUID().toString());
            //延迟投递时间
//            mapMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,3000);
            //重复投递时间间隔
//            mapMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,10000);
            //重复投递次数
//            mapMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,3);
            String messageID = mapMessage.getJMSMessageID();
            mapMessage.setString("msg1","sos");
            producer.send(mapMessage, new AsyncCallback() {
                //发送成功
                @Override
                public void onSuccess() {
                    System.out.println("success");
                }
                //发送失败
                @Override
                public void onException(JMSException exception) {
                    System.out.println("fail");
                }
            });
            System.out.println("生产者生产消息: "+mapMessage.toString());
        }
        producer.close();
        //创建session时transacted设置为true时必须提交或者回滚
        session.commit();
        session.close();
        connection.close();
    }
}
