1、springboot中参数转换

    a)将请求中的参数转换成Controller中方法参数
        通过request向后台发送请求时，一般请求参数都是string类型，但是在后台方法中参数比较多样化，比如对象、数组、集合等，如何在调用方法前将string数据进行转换，
     从而保证后台方法调用的正确
     目前提供以下几种方式：
        ①通过@InitBinder往WebDataBinder中注册对应的属性处理PropertyEditorSupport，在Controller中或者通过@ControllerAdvice中全局配置
        ②通过Converter、ConverterFactory、GenericConverter等相关接口中定义，然后通过WebMvcConfigurerAdapter注册
        ③通过HandlerMethodArgumentResolver扩展实现，在WebMvcConfigurerAdapter中注册
        ④通过一些特例简单处理，比如string->date 可以用@DateTimeFormat
        
2、原理分析

    以WebMvcAutoConfiguration配置为例。
    
    
        
        
    