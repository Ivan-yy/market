package com.yy.market.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan yu
 * @date 2020/08/01
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null){
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list1.add("add 2element"+i);
        }
        long start = System.currentTimeMillis();
        List<String> list2 = new ArrayList<>();
        list2.addAll(list1);
        System.out.println("cost: "+ (System.currentTimeMillis()-start) +"ms");
        System.gc();
    }
}
