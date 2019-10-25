##springboot 日志

#意义

    日志是记录系统运行过程状态的表现形式。适当的日志信息，可以知道系统目前运行状态，调用了什么方法，做了什么处理，是否出现故障等，日志提供了系统的可维护性。
    

#日志作用

    1、记录追踪排除问题
    2、构建预警系统
    3、数据提取,处理与分析


#日志级别

    日志按级别由高到底定义如下：
    OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL

#常用框架
    
    常见的日志框架主要包括三个方面的：日志门面、日志实现、日志适配
    
    日志门面：主要提供日志接口
        slf4j
            日志门面接口，直接实现为logback，如果与其他日志实现对接，需要相应的桥接包。
            编译时静态绑定具体实现，通过Resource获取class文件判断。
            比如系统使用的是commons-logging => log4j,通过slf4j将log4j => slf4j => logback，则需要引入包包括：log4j-slf4j、slf4j、logback
        commons-logging
            内部实现了与jul、log4j、avalon-Logkit、common-logging适配
            运行时（通过ClassLoader判断）动态根据LogFactory中getFactory(),决定具体使用上面哪种实现
            0）从系统配置HASHTABLE_IMPLEMENTATION_PROPERTY配置中加载，存在返回
            -> 从FACTORY_PROPERTIES加载prop配置 <-
            1）从系统属性加载FACTORY_PROPERTY
            2）通过SPI SERVICE_ID获取
            3）从prop配置中获取FACTORY_PROPERTY
            4）通过FACTORY_DEFAULT获取LogFactory实现类LogFactoryImpl,其中包括Log4JLogger、Jdk14Logger、Jdk13LumberjackLogger、SimpleLog，并且会按照以上顺序进行查找
            

    日志实现：完成日志的实际记录
        jul(java.util.logging)
            配置文件通过logging.properties控制，可以参考JUC中的说明，不依赖与其他第三方库
        log4j
            配置文件log4j.properties
        logback
            加载配置顺序logback-test.groovy, logback-test.xml, logback.groovy,logback.xml
            或者通过系统属性logback.configurationFile配置
        log4j2
        
        LogKit
        
    日志适配：完成各种实现与门面的适配
        比如常用的日志组合有 slf4j、logback；commons-logging、jul
        但是如果框架使用的是log4j，现在整个系统需要统一使用logback，那么我们需要引入slf4j-log4j12作为桥接，将log4j的日志转到slf4j，最终由logback实现输出
        
    

#实现原理
    
    主要分析slf4j:
    
    

#关系梳理
    单独使用：
        jul
        commons-logging
        log4j
        log4j2
    配合使用：
        commons-logging + (log4j、jul)
        slf4j + lobback
        slf4j + slf4j-simple
    桥接使用：
        slf4j + log4j -> slf4j、 slf4j-log4j12、log4j
        slf4j + log4j2 -> sl4j、 log4j-api 、log4j-core、log4j-slf4j-impl
        
        jul   => slf4j  (jul-to-slf4j)
        jcl   => slf4j  (jcl-over-slf4j)
        slf4j => log4j  (slf4j-log4j12)
        slf4j => log4j2 (log4j-slf4j-impl)
        log4j => slf4j  (log4j-over-slf4j)
        log4j2=> slf4j  (log4j-to-slf4j)
        
        * log4j-to-slf4j、log4j-slf4j-impl；log4j-over-slf4j、slf4j-log4j12不可同时使用

#springboot整合

    
