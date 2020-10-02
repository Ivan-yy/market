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
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(10,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return super.removeEldestEntry(eldest);
            }
        };
    }

}
