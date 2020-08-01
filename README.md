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
        
    可靠性: deliveryMode设置持久(默认)
        
    
        

