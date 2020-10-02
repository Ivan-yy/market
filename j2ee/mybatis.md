
Reflector: 对javaBean元数据属性封装类 defaultConstructor是bean无参构造方法

TypeHandlerRegistry: 主要负责管理所有已知的 TypeHandler，Mybatis 在初始化过程中会为所有已知的 TypeHandler 创建对象，并注册到 TypeHandlerRegistry。
自定义添加Typehandler可在在配置文件 mybatis-config.xml 中的 <typeHandler> 标签下配置好 自定义 TypeHandler，Mybatis 就会在初始化时解析该标签内容，完成 自定义 TypeHandler 的注册。

PooledConnection: jdk动态代理实现连接，当调用close方法时，并不会关闭连接，而是将其放回连接池。 realConnection是真正的连接对象。
PoolState: 管理连接对象状态

interface方法代理
    MapperRegistry: 是 Mapper 接口 及其对应的代理对象工厂的注册中心。Configuration 是 Mybatis 中全局性的配置对象，根据 Mybatis 的核心配置文件 mybatis-config.xml 解析而成。Configuration 通过 mapperRegistry 属性 持有该对象
    Mybatis 在初始化过程中会读取映射配置文件和 Mapper 接口 中的注解信息，并调用 MapperRegistry 的 addMappers()方法 填充 knownMappers 集合，在需要执行某 sql 语句 时，会先调用 getMapper()方法 获取实现了 Mapper 接口 的动态代理对象。
    MapperProxyFactory: 主要负责创建MapperProxy
    MapperProxy: 实现了 InvocationHandler 接口，为 Mapper 接口 的方法调用织入了统一处理。
    MapperMethod: 中封装了 Mapper 接口 中对应方法的信息，和对应 sql 语句 的信息，是连接 Mapper 接口 及映射配置文件中定义的 sql 语句 的桥梁。
    
缓存：
    MyBatis 中的缓存分为一级缓存、二级缓存，但在本质上是相同的，它们使用的都是 Cache 接口 的实现。MyBatis 缓存模块 的设计，使用了装饰器模式
    PerpetualCache: 被装饰的角色，使用hashmap记录缓存
    BlockingCache: 
假设 线程 A 从数据库中查找到 keyA 对应的结果对象后，将结果对象放入到 BlockingCache 中，此时 线程 A 会释放 keyA 对应的锁，唤醒阻塞在该锁上的线程。其它线程即可从 BlockingCache 中获取 keyA 对应的数据，而不是再次访问数据库。