package com.yy.market;

import com.yy.market.service.impl.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MarketApplicationTests {
    @Resource
    private ConsumerService consumerService;

    @Test
    void contextLoads() throws Exception{
        consumerService.produceMsg();
    }

}
