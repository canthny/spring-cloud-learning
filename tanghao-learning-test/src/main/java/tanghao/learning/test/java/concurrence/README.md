# java高并发学习

##缓存一致性协议
Intel的MESI协议，MESI协议保证了每个缓存中使用的共享变量的副本是一致的。当CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，会发出信号通知其他CPU将该变量的缓存行置为无效状态，因此当其他CPU需要读取这个变量时，发现自己缓存中缓存该变量的缓存行是无效的，那么它就会从内存重新读取。

##指令重排序

##原子性、可见性、有序性

每个线程有自己的工作内存（缓存），并不一定及时将修改写入主存

###------------------------------volatile关键字------------------------------
当一个共享变量被volatile修饰时：
1、他的修改会被立刻写入主存中；
2、会通知其他线程的缓存行中该变量的值无效；
3、其他线程会再次读取主存中的值，是最新的值，从而保证修改的可见性；
这里有个问题，如果当前线程sleep了，唤醒后会重新加载变量在主存中的值？貌似测试是这个情况，原理是什么

另外，如果能确保只有单个线程对共享的volatile变量执行写入操作，再结合他的可见性，即是线程安全的。

##线程封闭
Ad-hoc线程封闭，完全由程序实现来承担
栈封闭，基本类型的局部变量与引用变量的线程封闭性
ThreadLocal类，为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量

##委托
创建线程安全类的一个最有效的策略：只需让现有的线程安全类管理所有的状态即可。
Collections.synchronizedList就是采用这种方式，将状态封装起来，并对每个共有方法进行同步，可看源码便知。

##并发容器
迭代器与ConcurrentModificationException
ConcurrentHashMap
CopyOnWriteArrayList\CopyOnWriteArraySet容器每次修改时，都会创建并重新发布一个新的容器副本（事实不可变的对象）。迭代时不会与修改容器的线程相互干扰，并且返回的元素与迭代器创建时的元素一致。但是修改容器时复制底层数据开销较大，适用于迭代操作远多于修改的场景。
例子：事件通知系统，分发通知时需要迭代已注册监听器列表，通知操作一般会远大于监听器的注册和注销。

##阻塞队列BlockingQueue
生产者-消费者模式，当有界队列满了，无法生产时，应减轻负载，将多余的工作项写入磁盘，减少生产者数量，或者通过某种方式来抑制生产者线程。
LinkedBlockingQueue、ArrayBlockingQueue是FIFO队列
PriorityBlockingQueue优先级队列
SynchronousQueue，其实不是一个真正的队列，它不会为队列中的元素维护存储空间，它维护的是一组线程，线程在等着把元素加入或者移出队列。

#待研究的问题
Timer基于绝对时间
ScheduledThreadPoolExecutor基于相对时间