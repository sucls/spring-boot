springboot configure

相比springmvc，在springboot中最突出的处理内置了一些默认的配置外，就是免去了applicationContext.xml的配置，虽然通过配置形式从代码层面完成了
对象关系管理的解耦，但是还是会显得比较繁琐（springboot中bean的关系也非常繁琐）

在springboot中我们通过@Configuration标记一个类，对应与xml中的beans，而通过@Bean标记对应xml中的bean，这样怎么去使用就显得非常简单了。

当然，我们很多时候都会通过一些其它的配置来进行补充，让我们的配置更加的灵活与可控。

    1、@Conditional....
    2、@EnableConfigurationProperties
    3、@Import
    ...
 
#Conditional    
关于springboot常提到的一句话是“约定大于配置”，如果说spring(mvc)提供一套企业开发框架，而boot则提供了一套默认的解决方案。与其说约定不如说默认，
为了让开发者减少代码的开发量，以前大部分开发者需要的配置项现在被内置到框架内部，当然也不局限于框架本身，开发者可以根据个人需要覆盖默认实现，这一切
源于@Conditional，关于@Conditional相关的注解有以下几种：

    @Conditional
        通过实现org.springframework.context.annotation.Condition实现自定义的条件，非常灵活，下面的基本基于这个实现
    @ConditionalOnBean
        容器中存在指定的Bean，可以从CURRENT、ANCESTORS从查找
    @ConditionalOnClass
        classpath中存在对应的class，class没有load会主动去load
    @ConditionalOnCloudPlatform
        
    @ConditionalOnExpression
        表达式
    @ConditionalOnJava
        java版本
    @ConditionalOnJndi
        JNDI环境
    @ConditionalOnMissingBean
        容器中没有指定的Bean
    @ConditionalOnMissingClass
        不存在对应classs
    @ConditionalOnNotWebApplication
        不是web环境
    @ConditionalOnProperty
        存在指定属性
    @ConditionalOnResource
        存在指定文件
    @ConditionalOnSingleCandidate
        存在唯一bean
    @ConditionalOnWebApplication
        web环境
    @ConditionalOnRepositoryType
        
    @ConditionalOnEnabledResourceChain
        Spring Resource Handling chain
    @ConditionalOnMissingFilterBean
        没有指定的过滤链
    @ConditionalOnInitializedRestarter
        Restarter

#EnableConfigurationProperties、PropertySource
针对一些动态值，我们都会写到配置文件中，一般有常用的有yml、properties，通过@EnableConfigurationProperties和@ConfigurationProperties实现
属性文件的注入，当然，可以添加依赖spring-boot-configuration-processor，那么就可以在properties中根据java类对属性进行提示了
 1、@ConfigurationProperties + @EnableConfigurationProperties 相当于从全局中根据ConfigurationProperties的描述配置properties对象
 2、@PropertySource + @ConfigurationProperties + @Configuration 与上面类似，@PropertySource将属性文件注入到全局环境中，ConfigurationProperties配置
    属性文件，Configuration完成值的配置
    
   源码：PropertySource -> PropertySourceFactory
       
#Import    
@Import,看名字是和spring mvc中的xml导入一个道理,主要在AUTO时一次引入多个@Configuration，除此之外还有ImportSelector、ImportBeanDefinitionRegistrar
 ImportSelector：可以拿到该类相关的注解和与注解相关的方法等信息
 ImportBeanDefinitionRegistrar：比ImportSelector多一个BeanDefinitionRegistry，可以完成bean的注册
 
 一般configuration 与starter 配合使用
 
#Bean
将对象实例注册到spring容器，同<bean>

#Profile
应用环境，可以针对不同的开发环境设置不同的方案

#ImportResource
引入ApplicationContext.xml

#ComponentScan
包扫描，同<component-scan>

#Lazy
bean的延迟加载