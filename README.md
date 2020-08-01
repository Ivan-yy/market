# market

activemq:    
    消息头:
        deliveryMode: 持久(默认配置)，非持久（mq宕机消息丢失）
        priority: activemq默认4 0-4普通消息 5-9加急消息
        expiration: 过期时间
        messageId: 保证唯一性，消息幂等
        destination: 目的地
    消息体:
        textMessage: 字符串消息
        mapMessage: map
        bytesMessage: byte[]
        streamMessage: java流消息
        ObjectMessage: 可序列号对象
    消息属性:    
        property: 标记消息属性 
    topic: 消费者先订阅，生产者在发送。无论消费者是否在线都会接受到消息。不在线的话下次连接会把未接收的消息接收下来
    可靠性: 由以下几点保证 
        持久化(创建会话时自动默认开启)
        事务(transacted): 一般基于生产者端
        签收(acknowledge): 一般基于消费者端，事务设置为false时才有用，事务设置为true时会被ignore
            自动: 自动签收
            手动: 消费者消费完后调acknowledge方法mq才会认为消息被消费。事务设置为true时，只要事务提交了，不回调acknowledge也不会发生重复消费。(被ignore)
        
    
        

