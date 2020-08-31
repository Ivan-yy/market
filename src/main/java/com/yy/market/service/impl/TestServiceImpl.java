package com.yy.market.service.impl;

import com.yy.market.service.ITestService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan yu
 * @date 2020/08/25
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public List<String> doGet(String... str) {
        String[] str1 = str;
        System.out.println("doget");
        return Arrays.asList(str);
    }
}
