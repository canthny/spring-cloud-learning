## 基本命令
keys *：复杂度O(n)，会遍历所有键，线上键值多时禁止使用；
dbsize：复杂度O(1)，返回键的总数；
exists key：存在返回1，不存在返回0；
del key：删除key
expire key second：键过期
ttl key：查看键剩余过期时间，返回时间、-1键没设置过期时间、-2键不存在
type key：返回键类型，String、hash、list、set、zset(有序)集合

set key value [ex seconds] [px milliseconds] [nx|xx]
ex设置过期时间秒级，px过期时间毫秒级，nx键必须不存在才可以设置成功用于添加（可用于分布式锁），xx键必须存在才可以设置成功用于更新
mset key value [key value ...]：批量设置
mget key [key ...]：批量获取

incr key：对值做自增操作，8值不是整数返回错误、值是整数返回自增后的结果，健不存在按照值为0自增返回结果1
decr key：自减
incrby key increment：自增指定的数字
decrby key decrement：自减指定的数字
increbyfloat key increment：自增浮点数

## 单线程架构，I/O多路复用模型（epoll）
为什么快？纯内存访问，非阻塞I/O，单线程避免线程切换和竟态产生的消耗
一条命令从客户端达到服务端并不会被立刻执行，而是进入一个队列中，然后被逐个执行；
所以客户端命令的执行顺序是不确定的，但是因为是单线程执行所以最终结果是确定的；

场景：
1、缓存功能；
2、计数，唯一流水号等；
3、共享session；
4、分布式锁；

hset key field value：键值本身是一个键值对，即value={{field1,value1},,{fieldN,valueN}}
hget key field：获取键值value中field域的值
hdel key field [field ...]：删除一个或多个field域
hlen key：获取field域个数
hmset key field value [field value ...]：批量设置
hmget key field [field ...]：批量获取
hkeys key：获取key下的所有field域
hexists key field：判断域是否存在
hvals key：获取所有value

hash类型内部有两种编码：ziplist(压缩列表，哈希类型元素个数小于512个同时所有值小于64字节时使用)、hashtable(哈希表)

rpush key value [value ...]：从右边插入元素，lpush从左边
linsert key before|after pivot value：向某个元素前或后插入元素
lrange key start end：获取指定范围内的元素列表，从0开始
lindex key index：获取列表指定索引下标的元素
llen key：获取列表长度
lpop key：从列表左侧拉出元素
lrem key count value：删除指定元素，从列表中找到等于value的元素进行删除，count>0从左到右删除最多count个
					  count<0从右到左删除最多count绝对值个，count=0全部删除
ltrim key start end：按照索引范围裁剪列表，即保留start到end间的元素
lset key index newvalue：修改指定索引下标的元素

blpop key [key ...] timeout
brpop key [key ...] timeout
1）列表为空：客户端需要等待timeout秒后返回null,若timeout为0则一直阻塞等下去
2）列表部位空：客户端会立即返回

列表类型内部也有两种编码：ziplist、linkedlist(链表)、3.2版本后提供了quicklist
使用场景：
1、消息队列
lpush+brpop命令组合即可实现阻塞队列，生产者客户端使用lpush从左侧插入元素，多个消费者客户端使用brpop命令阻塞式的抢列表尾部元素，每个客户端保证了消费的负载均衡和高可用性
2、
