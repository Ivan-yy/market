package com.yy.market;

import com.yy.market.service.impl.ConsumerService;
import com.yy.market.service.impl.ProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MarketApplicationTests {
    @Resource
    private ProviderService providerService;
    @Resource
    private ConsumerService consumerService;

    @Test
    void produce() throws Exception{
        providerService.produceMsgSchedule();
    }

    @Test
    void consume()throws Exception{

    }

}
