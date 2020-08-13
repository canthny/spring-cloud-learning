#
- IOC&依赖注入  
-- 定义:bean的名称和对应的Class  
-- 实例化:根据定义实例化一个指定class的对象，并放入factory缓存中  
-- 初始化:属性变量赋值，初始化方法  
-- 容器:添加、缓存、获取bean，解决循环依赖  
-- 使用:直接注入即可

- AOP
-- 代理对象的创建：扫描所有bean时要把aspect切的类方法标记，示例化bean前判断是否需要创建代理对象。  
-- before & after方法的实现：bean代理中在执行方法前，先调用aspect的before和after方法；  
-- around 方法的实现：将被代理的bean方法丢到aspect方法的请求参数里，在aspect方法中通过methodInvoke调用。