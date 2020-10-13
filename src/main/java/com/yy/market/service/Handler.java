package com.yy.market.service;

import com.yy.market.service.impl.TestServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ivan yu
 * @date 2020/08/25
 */
public class Handler implements InvocationHandler {

    private Object target;
    public Handler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        System.out.println(name+"--前置通知-");
        Object invoke = method.invoke(target, args);
        System.out.println("---后置通知--");
        return invoke;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) throws Exception{
        ITestService testService = new TestServiceImpl();
        ITestService proxyInstance = (ITestService) new Handler(testService).getProxyInstance();
        System.out.println(proxyInstance.doGet("x","1","2"));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(8,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 5;
            }
        };
        linkedHashMap.put("a","999");

        linkedHashMap.put("b","999");
        linkedHashMap.put("c","999");
        linkedHashMap.put("d","999");
        linkedHashMap.put("e","999");
        linkedHashMap.get("a");
        linkedHashMap.put("f","999");
        linkedHashMap.put("g","999");
        linkedHashMap.put("h","999");
        System.out.println(linkedHashMap);
    }

}
