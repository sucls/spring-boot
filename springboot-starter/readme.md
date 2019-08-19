##springboot starter

对于spring的项目，我们大多数情况下需要完成的工作是如何将bean注入spring容器或者从容器中安类型或bean name取出实例，也就是我们所说的DI。
在如今项目依赖愈发庞杂的情况下，我们最容易也最多的是将项目进行拆分，看spring frameworker 就可以知道，其核心由多个模块构成，同时衍生出
很多的模块与其它框架进行整合与扩展，那么回到上面的问题，如何将扩展的模块关系的bean交给spring ico，starter就可以帮我们实现。

先回忆一下，在之前springmvc时代，如果项目中我们有多个模块是如何实现，是否需要给每个模块配置一个applicationContext.xml，然后在主模块中
通过import引入各个子模块的xml。现在没有了xml我们怎么实现这个？

在对starter了解之前，有必要知道一个东西,SPI(Service Provider Interface)，这个不属于spring，而是由java提供的一套“接口＋策略模式＋配置文件”
实现动态加载的机制。简单的说就是实现了 接口（抽象）与实现的解耦。

    示例：
    1、定义接口 InfoProvider
    2、在classpath，也就是src/main/resources中新建目录META-INF/services
    3、在上一目录创建文件，命名InfoProvider全限名 com.sucl.starter.spi.InfoProvider
    4、写两个InfoProvider的实现类StaticInfoProvider、ClassInfoProvider
    5、分别将以上两个实现的全名写入com.sucl.starter.spi.InfoProvider文件中
    6、通过ServiceLoader获取实现
    7、Junit测试
    
大致了解了SPI后再看看springboot中的使用，在spring-boot-autoconfigure中，META-INF/spring.factories,这个可以说是spring spi，与上面ServiceLoader
对应SpringFactoriesLoader，实现上都是通过反射，只不过spring功能更强大。

在spring.factories中可以看到很多默认的策略，比如我们EnableAutoConfiguration对应了很多auto相关的实现,具体细节如何实现可以阅读源码。

现在我们有一个新的模块，如何与我们项目关联，如果单纯的将模块打成jar引入到项目，你会发现，该模块中的bean是没办法注入当前项目的容器中，那么我们可以做个测试

    示例：
    1、新建模块spring-boot-simple-starter
    2、创建一个简单的服务SimpleService、实现SimpleServiceImpl，创建bean的配置文件SimpleAutoConfiguration将服务注册到IOC
    3、在src/main/resources下创建META-INF/spring.factories，写入
        org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
          com.sucl.simplestarter.SimpleAutoConfiguration
    4、在本pom.xml中引入刚的模块
    5、测试
    
总结：springboot starter主要完成模块引入时注册到spring ico
    