package com.yy.market.service.impl;

import org.apache.activemq.broker.BrokerService;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception{
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
