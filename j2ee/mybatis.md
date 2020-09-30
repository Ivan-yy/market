
Reflector: 对javaBean元数据属性封装类 defaultConstructor是bean无参构造方法

TypeHandlerRegistry: 主要负责管理所有已知的 TypeHandler，Mybatis 在初始化过程中会为所有已知的 TypeHandler 创建对象，并注册到 TypeHandlerRegistry。
自定义添加Typehandler可在在配置文件 mybatis-config.xml 中的 <typeHandler> 标签下配置好 自定义 TypeHandler，Mybatis 就会在初始化时解析该标签内容，完成 自定义 TypeHandler 的注册。

PooledConnection: jdk动态代理实现连接，当调用close方法时，并不会关闭连接，而是将其放回连接池。 realConnection是真正的连接对象。
PoolState: 管理连接对象状态