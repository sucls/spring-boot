参数

    corePoolSize：核心线程数
                核心线程会一直存活，及时没有任务需要执行
                当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
                设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
    queueCapacity：任务队列容量（阻塞队列）
                当核心线程数达到最大时，新任务会放在队列中排队等待执行
    maxPoolSize：最大线程数
                当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
                当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
    keepAliveTime：线程空闲时间
                当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
                如果allowCoreThreadTimeout=true，则会直到线程数量=0
    allowCoreThreadTimeout：允许核心线程超时
    rejectedExecutionHandler：任务拒绝处理器
                两种情况会拒绝处理任务：
                当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
                当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
                线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
    ThreadPoolExecutor类有几个内部实现类来处理这类情况：
    AbortPolicy 丢弃任务，抛运行时异常
    CallerRunsPolicy 执行任务
    DiscardPolicy 忽视，什么都不会发生
    DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
                实现RejectedExecutionHandler接口，可自定义处理器

执行原理：

    当线程数小于核心线程数时，创建线程。
    当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
    当线程数大于等于核心线程数，且任务队列已满
    若线程数小于最大线程数，创建线程
    若线程数等于最大线程数，抛出异常，拒绝任务

参考：
    
    https://www.cnblogs.com/syp172654682/p/9383335.html

参数：

     *缓存队列：
     * ArrayBlockingQueue：基于数组的FIFO队列，是有界的，创建时必须指定大小
     * LinkedBlockingQueue： 基于链表的FIFO队列，是无界的，默认大小是 Integer.MAX_VALUE
     * synchronousQueue:一个比较特殊的队列，虽然它是无界的，但它不会保存任务，每一个新增任务的线程必须等待另一个线程取出任务，也可以把它看成容量为0的队列
     * DelayQueue: 延迟队列java.util.concurrent.Delayed 接口
     * PriorityBlockingQueue :可排序的任务
     *
     *拒绝策略：
     * ThreadPoolExecutor.AbortPolicy() 抛出RejectedExecutionException异常
     * ThreadPoolExecutor.CallerRunsPolicy() 由向线程池提交任务的线程来执行该任务
     * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃最旧的任务（最先提交而没有得到执行的任务）
     * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务

在springboot中的使用

    1、通过 @EnableAsync @Async即可实现方法异步调用，方法支持void与Future
    2、不做配置时使用默认线程池
    3、通过AsyncConfigurer自定义线程池与异常处理模式（无返回），有返回值的方法可以通过get时的try-cache获取并处理异常
    4、@Async可以指定线程池
    5、注意ThreadPoolTaskExecutor的使用，与ThreadPoolExecutor类似，都属于ExecutorService的实现