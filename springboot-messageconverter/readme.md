1、MessageConverter作用
    
    准确的说应该是HttpMessageConverter接口，主要针对@ResponseBody修饰的类或方法，通过RequestResponseBodyMethodProcessor实现将结果集由HttpMessageConverter
    转换成json返回到界面

2、在springboot如何自动配置
    
    HttpMessageConvertersAutoConfiguration
    具体如何注入spring容器中，可以参考WebMvcAutoConfiguration

3、实现原理

    在DispetcherServlet中，通过handler处理相应的请求后都有对应的返回值，所有返回值都会通过HandlerMethodReturnValueHandler进行处理，处理完成后有的直接将结果写入
    Response，有的返回一个ModelAndView对象。
    源码过程：
       首先在DispetcherServlet -> doDispatch ->通过不同的请求适配到对应的HandlerAdapter
       HandlerAdapter.handle -> AbstractHandlerMethodAdapter.handleInternal ->  RequestMappingHandlerAdapter.invokeHandlerMethod
       -> ServletInvocableHandlerMethod.invokeAndHandle -> HandlerMethodReturnValueHandlerComposite.handleReturnValue->
       找到所有HandlerMethodReturnValueHandler，适配满足要求的那个，也就是RequestResponseBodyMethodProcessor,进而调用handleReturnValue
       将请求响应转换成Message形式，最终通过AbstractMessageConverterMethodProcessor.writeWithMessageConverters找到对应的HttpMessageConverter
         

4、扩展及配置

    自定义HttpMessageConverter的实现类，并在WebMvcConfigurerAdapter中配置

5、注意事项

    configureMessageConverters：重新配置覆盖springboot默认配置
    extendMessageConverters：在springboot默认的配置上进行扩展