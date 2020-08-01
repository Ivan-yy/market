package com.yy.market.service.impl;

import com.yy.market.service.ITestService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.jms.*;


/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Service
public class TestServiceImpl implements ITestService{
    @Value("${queue}")
    private String queueName;

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("activemq-queue");
        MessageProducer producer = session.createProducer(queue);

        //Topic topic = session.createTopic("activemq-topic");
        //默认配置
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (int i = 0; i < 3; i++) {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("msg1","sos");
            producer.send(mapMessage);


        }
        producer.close();
        session.close();
        connection.close();
    }
}
