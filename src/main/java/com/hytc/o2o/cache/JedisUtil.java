package com.hytc.o2o.cache;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisUtil {

    /**
     * 缓存生成时间
     */
    @Value("${redis.expire}")
    private int expire;

    /**
     * 链接池
     */
    private JedisPool jedisPool;

    /**
     * 操作Key的方法
     */
    public Keys KEYS;

    /**
     * 对存储机构为String类型的操作
     */
    public Strings STRINGS;

    /**
     * 对存储机构为LISTS类型的操作
     */
    public Lists LISTS;

    /**
     * 对存储机构为SETS类型的操作
     */
    public Sets SETS;

    /**
     * 对存储机构为HASH类型的操作
     */
    public Hash HASHS;


    public JedisUtil() {
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriter jedisPoolWriter) {
        this.jedisPool = jedisPoolWriter.getJedisPool();
    }

    /**
     * 从JedisPool中获取Jedis对象
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 设定指定的过期时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }

    /**
     * 设置默认过期时间
     *
     * @param key
     * @author ruan 2013-4-11
     */
    public void expire(String key) {
        expire(key, expire);
    }

    /**-----------------------------------------KEYS--------------------------------------*/
    /**
     * 内部类 KEYS
     */
    public class Keys {

        public Keys(JedisUtil jedisUtils) {

        }

        /**
         * 清楚所有Key
         */
        public String flushAll() {
            //Jedis jedis = getJedis();
            Jedis jedis = getJedis();
            String status = jedis.flushAll();
            jedis.close();
            return status;
        }

        /**
         * 重载rename，支持String字符串
         *
         * @param oldName
         * @param newName
         * @return
         */
        public String rename(String oldName, String newName) {
            return rename(SafeEncoder.encode(oldName), SafeEncoder.encode(newName));
        }

        /**
         * 更改key
         *
         * @param oldName
         * @param newName
         * @return
         */
        public String rename(byte[] oldName, byte[] newName) {
            Jedis jedis = getJedis();
            String status = jedis.rename(oldName, newName);
            jedis.close();
            return status;
        }

        /**
         * 更改key,仅当新key不存在时才执行
         *
         * @param oldName
         * @param newName
         * @return
         */
        public Long renamex(String oldName, String newName) {
            Jedis jedis = getJedis();
            Long status = jedis.renamenx(oldName, newName);
            jedis.close();
            return status;
        }

        /**
         * 更改key,仅当新key不存在时才执行
         *
         * @param oldName
         * @param newName
         * @return
         */
        public Long renamex(byte[] oldName, byte[] newName) {
            Jedis jedis = getJedis();
            Long status = jedis.renamenx(oldName, newName);
            jedis.close();
            return status;
        }

        /**
         * 将Key是指过期时间，
         *
         * @param key
         * @param second
         * @return 影响行数
         */
        public long expire(String key, int second) {
            Jedis jedis = getJedis();
            Long count = jedis.expire(key, second);
            jedis.close();
            return count;
        }

        /**
         * 设置Key的过期时间，是与格林威治时间的标准偏移量
         *
         * @param key
         * @param timestamp 时间
         * @return 影响行数
         */
        public long expireAt(String key, long timestamp) {
            Jedis jedis = getJedis();
            Long count = jedis.expireAt(key, timestamp);
            jedis.close();
            return count;
        }

        /**
         * 查询Key的过期时间
         *
         * @param key
         * @return 过期时间（秒）
         */
        public Long ttl(String key) {

            Jedis jedis = getJedis();

            Long time = jedis.ttl(key);

            jedis.close();
            return time;
        }

        /**
         * 取消对Key过期时间的设置
         *
         * @param key
         * @return 影响行数
         */
        public long persist(String key) {
            Jedis jedis = getJedis();
            long count = jedis.persist(key);
            jedis.close();
            return count;
        }

        /**
         * 删除多个Key
         *
         * @param keys （...多个）String
         * @return 删除的记录数
         */
        public long del(String... keys) {
            Jedis jedis = getJedis();
            Long count = jedis.del(keys);
            jedis.close();
            return count;
        }


        /**
         * 删除多个Key
         *
         * @param keys （...多个） byte[]
         * @return 删除的记录数
         */
        public long del(byte[]... keys) {
            Jedis jedis = getJedis();
            Long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        /**
         * 判断是否存在
         *
         * @param key
         * @return
         */
        public boolean exists(String key) {

            Jedis jedis = getJedis();
            Boolean isExit = jedis.exists(key);
            jedis.close();
            return isExit;
        }

        /**
         * 对List Set SortSet进行排序，如果集合数据量比较大尽量不要使用这个方法
         *
         * @param key
         * @return List<String> 集合的全部记录
         */
        public List<String> sort(String key) {
            Jedis jedis = getJedis();
            List<String> list = jedis.sort(key);
            jedis.close();
            return list;
        }

        /**
         * 对List,Set,SortSet进行排序或limit
         *
         * @param key
         * @param parame parame定义排序类型或者limit的起止位置
         * @return List<String> 全部或者部分记录
         */
        public List<String> sort(String key, SortingParams parame) {
            Jedis jedis = getJedis();
            List<String> list = jedis.sort(key, parame);
            jedis.close();
            return list;
        }

        /**
         * 返回指定Key存储的类型
         *
         * @param key
         * @return String  string|list|set|zset|hash
         */
        public String type(String key) {

            Jedis jedis = getJedis();
            String type = jedis.type(key);
            jedis.close();
            return type;
        }

        /**
         * 查找所匹配的给定模式的键
         *
         * @param pattern
         * @return
         */
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }

    }


    /**-----------------------------------------STRINGS--------------------------------------*/
    /**
     * 内部类 STRINGS
     */
    public class Strings {

        public Strings(JedisUtil jedisUtils) {
        }

        /**
         * 根据Key获取记录
         *
         * @param key
         * @return 值
         */
        public String get(String key) {
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();
            return value;
        }

        public byte[] get(byte[] key) {
            Jedis jedis = getJedis();
            byte[] value = jedis.get(key);
            jedis.close();
            return value;
        }

        /**
         * 添加有过期时间的记录<br/>
         *
         * @param key
         * @param seconds
         * @param value
         * @return
         */
        public String setEx(String key, int seconds, String value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        /**
         * 添加有过期时间的记录 <br/>
         *
         * @param key
         * @param seconds 过期时间，以秒为单位
         * @param value
         * @return String 操作状态
         */
        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        /**
         * 添加一条激励，进党给定的Key不存在时才插入 <br/>
         *
         * @param key
         * @param value
         * @return
         */
        public long setNx(String key, String value) {
            Jedis jedis = getJedis();
            long str = jedis.setnx(key, value);
            jedis.close();
            return str;
        }

        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         *
         * @param key
         * @param value
         * @return 状态码
         */
        public String set(String key, byte[] value) {
            return set(SafeEncoder.encode(key), value);
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         *
         * @param key
         * @param value
         * @return 状态码
         */
        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }

        /**
         * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
         * 例如： String str = "1234567890";
         * setRange(str1,4,0000),str1="123400009";
         *
         * @param key
         * @param offset
         * @param value
         * @return long value的长度
         */
        public long setRange(String key, long offset, String value) {
            Jedis jedis = getJedis();

            long len = jedis.setrange(key, offset, value);

            jedis.close();

            return len;
        }

        /**
         * 在指定的key中追加value
         *
         * @param key
         * @param value
         * @return
         */
        public long append(String key, String value) {
            Jedis jedis = getJedis();
            long len = jedis.append(key, value);
            jedis.close();
            return len;
        }

        /**
         * 将Key对应的value减去指定的值，只有value可以转换为数字的时候可以使用
         *
         * @param key
         * @param number
         * @return
         */
        public long decrBy(String key, long number) {
            Jedis jedis = getJedis();
            long result = jedis.decrBy(key, number);
            jedis.close();
            return result;
        }

        /**
         * <b>可以作为获取唯一id的方法</b> <br/>
         * 将key对应的value加上指定的值，只有value可以转换成数字时刻该方法才可以用
         *
         * @param key
         * @param number 需要减去的值
         * @return 相加后的值
         */
        public long incrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.incrBy(key, number);
            jedis.close();
            return len;
        }

        /**
         * 对指定key对应的value进行截取
         *
         * @param key
         * @param startOffset 开始位置
         * @param endOffset   结束位置
         * @return 截取的值
         */
        public String getrange(String key, long startOffset, long endOffset) {
            Jedis jedis = getJedis();
            String value = jedis.getrange(key, startOffset, endOffset);
            jedis.close();
            return value;
        }

        /**
         * 获取并设置指定key对应的value<br/>
         * 如果key存在返回之前的value,否则返回null
         *
         * @param key
         * @param value
         * @return String 原始value或null
         */
        public String getSet(String key, String value) {
            Jedis jedis = getJedis();
            String str = jedis.getSet(key, value);
            jedis.close();
            return str;
        }

        /**
         * 批量获取记录，如果指定的key不存在返回list所在的值或者是null
         *
         * @param keys
         * @return
         */
        public List<String> mget(String... keys) {
            Jedis jedis = getJedis();
            List<String> list = jedis.mget(keys);
            jedis.close();
            return list;
        }

        /**
         * 批量存储记录
         *
         * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
         * @return String 状态码
         */
        public String mset(String... keysvalues) {
            Jedis jedis = getJedis();
            String str = jedis.mset(keysvalues);
            jedis.close();
            return str;
        }

        /**
         * 获取key对应的值的长度
         *
         * @param key
         * @return
         */
        public long strlen(String key) {
            Jedis jedis = getJedis();
            long len = jedis.strlen(key);
            jedis.close();
            return len;
        }

    }

    /**-----------------------------------------LISTS--------------------------------------*/
    /**
     * 内部类 LISTS
     */
    public class Lists {
        public Lists(JedisUtil jedisUtils) {
        }

        /**
         * List长度
         *
         * @param key
         * @return
         */
        public long llen(String key) {
            return llen(SafeEncoder.encode(key));
        }

        /**
         * List长度
         *
         * @param key
         * @return 长度
         */
        public long llen(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long count = sjedis.llen(key);
            sjedis.close();
            return count;
        }

        /**
         * 覆盖操作，
         *
         * @param key
         * @param index
         * @param value
         * @return
         */
        public String lset(byte[] key, int index, byte[] value) {

            Jedis jedis = getJedis();
            String status = jedis.lset(key, index, value);
            jedis.close();
            return status;
        }

        /**
         * 覆盖操作,将覆盖List中指定位置的值
         *
         * @param key
         * @param index 位置
         * @param value 值
         * @return 状态码
         */
        public String lset(String key, int index, String value) {
            return lset(SafeEncoder.encode(key), index,
                    SafeEncoder.encode(value));
        }

        /**
         * 在value的相对位置插入记录
         *
         * @param key
         * @param where 前面插入或后面插入
         * @param pivot 相对位置的内容
         * @param value 插入的内容
         * @return 记录总数
         */
        public long linsert(String key, LIST_POSITION where, String pivot,
                            String value) {
            return linsert(SafeEncoder.encode(key), where,
                    SafeEncoder.encode(pivot), SafeEncoder.encode(value));
        }

        /**
         * 在指定位置插入记录
         *
         * @param key
         * @param where 前面插入或后面插入
         * @param pivot 相对位置的内容
         * @param value 插入的内容
         * @return 记录总数
         */
        public long linsert(byte[] key, LIST_POSITION where, byte[] pivot,
                            byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.linsert(key, where, pivot, value);
            jedis.close();
            return count;
        }

        /**
         * 获取List中指定位置的值
         *
         * @param key
         * @param index 位置
         * @return 值
         **/
        public String lindex(String key, int index) {
            return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
        }

        /**
         * 获取List中指定位置的值
         *
         * @param key
         * @param index 位置
         * @return 值
         **/
        public byte[] lindex(byte[] key, int index) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.lindex(key, index);
            sjedis.close();
            return value;
        }

        /**
         * 将List中的第一条记录移出List
         *
         * @param key
         * @return 移出的记录
         */
        public String lpop(String key) {
            return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
        }

        /**
         * 将List中的第一条记录移出List
         *
         * @param key
         * @return 移出的记录
         */
        public byte[] lpop(byte[] key) {
            Jedis jedis = getJedis();
            byte[] value = jedis.lpop(key);
            jedis.close();
            return value;
        }

        /**
         * 将List中最后第一条记录移出List
         *
         * @param key
         * @return 移出的记录
         */
        public String rpop(String key) {
            Jedis jedis = getJedis();
            String value = jedis.rpop(key);
            jedis.close();
            return value;
        }

        /**
         * 向List尾部追加记录
         *
         * @param key
         * @param value
         * @return 记录总数
         */
        public long lpush(String key, String value) {
            return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }


        /**
         * 向List头部追加记录
         *
         * @param String key
         * @param String value
         * @return 记录总数
         */
        public long rpush(String key, String value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        /**
         * 向List头部追加记录
         *
         * @param key
         * @param value
         * @return 记录总数
         */
        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        /**
         * 向List中追加记录
         *
         * @param key
         * @param value
         * @return 记录总数
         */
        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lpush(key, value);
            jedis.close();
            return count;
        }


        /**
         * 获取指定范围的记录，可以做为分页使用
         *
         * @param key
         * @param start
         * @param end
         * @return List
         */
        public List<String> lrange(String key, long start, long end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        /**
         * 获取指定范围的记录，可以做为分页使用
         *
         * @param key
         * @param start
         * @param end   如果为负数，则尾部开始计算
         * @return List
         */
        public List<byte[]> lrange(byte[] key, int start, int end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        /**
         * 删除List中c条记录，被删除的记录值为value
         *
         * @param key
         * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
         * @param value 要匹配的值
         * @return 删除后的List中的记录数
         */
        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lrem(key, c, value);
            jedis.close();
            return count;
        }

        /**
         * 删除List中c条记录，被删除的记录值为value
         *
         * @param key
         * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
         * @param value 要匹配的值
         * @return 删除后的List中的记录数
         */
        public long lrem(String key, int c, String value) {
            return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        /**
         * 算是删除吧，只保留start与end之间的记录
         *
         * @param key
         * @param start 记录的开始位置(0表示第一条记录)
         * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
         * @return 执行状态码
         */
        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = getJedis();
            String str = jedis.ltrim(key, start, end);
            jedis.close();
            return str;
        }

        /**
         * 算是删除吧，只保留start与end之间的记录
         *
         * @param key
         * @param start 记录的开始位置(0表示第一条记录)
         * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
         * @return 执行状态码
         */
        public String ltrim(String key, int start, int end) {
            return ltrim(SafeEncoder.encode(key), start, end);
        }

    }
    /**-----------------------------------------SETS--------------------------------------*/
    /**
     * 内部类 Sets
     */
    public class Sets {
        public Sets(JedisUtil jedisUtils) {
        }

        /**
         * 向Set中添加一条记录，如果member已经存在返回0，否则返回1
         *
         * @param key
         * @param member
         * @return
         */
        public long sadd(String key, String member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        /**
         * 向Set中添加一条记录，如果member已经存在返回0，否则返回1
         *
         * @param key
         * @param member
         * @return
         */
        public long sadd(byte[] key, byte[] member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        /**
         * 返回Redis中给定的Key的元素的个数
         *
         * @param key
         * @return
         */
        public long scard(String key) {
            Jedis jedis = getJedis();
            long count = jedis.scard(key);
            jedis.close();
            return count;
        }

        /**
         * 返回第一组和Redis中不一样的集合
         *
         * @param keys
         * @return
         */
        public Set<String> sdiff(String... keys) {
            Jedis jedis = getJedis();
            Set<String> sets = jedis.sdiff(keys);
            jedis.close();
            return sets;
        }

        /**
         * 找出与keys不一样的并存储到新的集合之中，如果集合存在就覆盖
         *
         * @param keys
         * @return 新集合的记录总数
         */
        public long sdiffStore(String keys) {
            Jedis jedis = getJedis();
            long count = jedis.sdiffstore(keys);
            jedis.close();
            return count;
        }

        /**
         * 返回给定结合交集的成员，如果其中一个结合为空，返回空的Set
         *
         * @param keys
         * @return 交集成员的集合
         */
        public Set<String> sinter(String... keys) {
            Jedis jedis = getJedis();
            Set<String> sets = jedis.sinter(keys);
            jedis.close();
            return sets;
        }

        /**
         * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
         *
         * @param newkey 新结果集的key
         * @param keys   比较的集合
         * @return 新集合中的记录数
         **/
        public long sinterstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.sinterstore(newkey, keys);
            jedis.close();
            return count;
        }

        /**
         * 判断某个是不是村赞
         *
         * @param key
         * @param member
         * @return
         */
        public boolean sismember(String key, String member) {
            Jedis jedis = getJedis();
            boolean isStore = jedis.sismember(key, member);
            jedis.close();
            return isStore;
        }

        /**
         * 返回key的所有成员
         *
         * @param key
         * @return
         */
        public Set<String> smembers(String key) {
            Jedis jedis = getJedis();
            Set<String> sets = jedis.smembers(key);
            jedis.close();
            return sets;
        }

        public Set<byte[]> smembers(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<byte[]> set = sjedis.smembers(key);
            sjedis.close();
            return set;
        }

        /**
         * 将成员从原集合移除放入目标集合<br/>
         * 1。如果源集合不存在指定成员，则不进行任何操作返回0<br/>
         * 2。如果源集合存在指定成员，删除该成员，并添加到目标集合，如果目标集合已经存在，则只在原集合删除<br/>
         *
         * @param srckey 源集合
         * @param dstkey 目标集合
         * @param member 源集合中的成员
         * @return 1 成功  0 失败
         */
        public long smove(String srckey, String dstkey, String member) {
            Jedis jedis = getJedis();
            long status = jedis.smove(srckey, dstkey, member);
            jedis.close();
            return status;
        }

        /**
         * 从集合中删除元素
         *
         * @param key
         * @return 被删除的成员
         */
        public String sprop(String key) {
            Jedis jedis = getJedis();
            String member = jedis.spop(key);
            jedis.close();
            return member;
        }

        /**
         * 删除指定的成员
         *
         * @param key
         * @param member
         * @return 成功 1 失败 0
         */
        public long srem(String key, String member) {
            Jedis jedis = getJedis();
            long status = jedis.srem(key, member);
            jedis.close();
            return status;
        }

        /**
         * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
         *
         * @param keys
         * @return
         */
        public Set<String> sunion(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sunion(keys);
            jedis.close();
            return set;
        }

        /**
         * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
         *
         * @param newkey newkey 新集合的key
         * @param keys   ... keys 要合并的集合
         **/
        public long sunionstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sunionstore(newkey, keys);
            jedis.close();
            return s;
        }
    }

    /**-----------------------------------------HASH--------------------------------------*/
    /**
     * 内部类 Hash
     */
    public class Hash {
        public Hash(JedisUtil jedisUtils) {
        }

        /**
         * 从哈说中删除指定的存储
         *
         * @param key
         * @param fieid
         * @return 状态码
         */
        public long hdel(String key, String fieid) {

            Jedis jedis = getJedis();
            long status = jedis.hdel(key, fieid);
            jedis.close();
            return status;
        }

        public long hdel(String key) {
            Jedis jedis = getJedis();
            long s = jedis.del(key);
            jedis.close();
            return s;
        }

        /**
         * 判断是否存在
         *
         * @param key
         * @param field
         * @return
         */
        public boolean hexists(String key, String field) {
            Jedis jedis = getJedis();
            boolean flag = jedis.hexists(key, field);
            jedis.close();
            return flag;
        }

        /**
         * 返回hash中指定存储位置的值
         *
         * @param key
         * @param fieid
         * @return
         */
        public String hget(String key, String fieid) {
            Jedis jedis = getJedis();
            String s = jedis.hget(key, fieid);
            jedis.close();
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] s = sjedis.hget(key, fieid);
            sjedis.close();
            return s;
        }

        /**
         * 获取所有的 key对应的存储和值
         *
         * @param key
         * @return
         */
        public Map<String, String> hGetAll(String key) {
            Jedis jedis = getJedis();
            Map<String, String> map = jedis.hgetAll(key);
            jedis.close();
            return map;
        }

        /**
         * 添加一个对应关系
         *
         * @param key
         * @param fieid
         * @param value
         * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
         **/
        public long hset(String key, String fieid, String value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key, fieid, value);
            jedis.close();
            return s;
        }

        public long hset(String key, String fieid, byte[] value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            jedis.close();
            return s;
        }

        /**
         * 添加对应关系，只有在fieid不存在的时候才执行 <br/>
         *
         * @param key
         * @param field
         * @param value
         * @return
         */
        public long hsetnx(String key, String field, String value) {
            Jedis jedis = getJedis();
            long status = jedis.hsetnx(key, field, value);
            jedis.close();
            return status;
        }

        /**
         * 获取Hash中的valuess
         *
         * @param key
         * @return
         */
        public List<String> hvals(String key) {
            Jedis jedis = getJedis();
            List<String> list = jedis.hvals(key);
            jedis.close();
            return list;
        }

        /**
         * 在指定的存储位置加上指定的数字。存储位置的值必须可转换成数字类型 <br/>
         *
         * @param key
         * @param fieid
         * @param value
         * @return
         */
        public long hincrby(String key, String fieid, long value) {
            Jedis jedis = getJedis();
            long s = jedis.hincrBy(key, fieid, value);
            jedis.close();
            return s;
        }

        /**
         * 返回指定hash中的所有的存储名称，类似于Map中的keySet方法
         *
         * @param key
         * @return
         */
        public Set<String> hkeys(String key) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.hkeys(key);
            jedis.close();
            return set;
        }

        /**
         * 获取hash中存储的个数，类似于Map.size()方法<br/>
         *
         * @param key
         * @return
         */
        public long hlen(String key) {
            Jedis jedis = getJedis();
            long len = jedis.hlen(key);
            jedis.close();
            return len;
        }

        /**
         * 根据多个Key，获取对应的value，返回list，如果指定的key不存在，List对应位置为null
         *
         * @param key
         * @param fieid 存储位置
         * @return
         */
        public List<String> hmget(String key, String... fieid) {
            Jedis jedis = getJedis();
            List<String> list = jedis.hmget(key, fieid);
            jedis.close();
            return list;
        }

        public List<byte[]> hmget(byte[] key, byte[]... fieids) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.hmget(key, fieids);
            sjedis.close();
            return list;
        }

        /**
         * 添加对应关系，如果关系已经存在，则覆盖
         *
         * @param key
         * @param map
         * @return 状态，成功返回OK
         */
        public String hmset(String key, Map<String, String> map) {
            Jedis jedis = getJedis();
            String status = jedis.hmset(key, map);
            jedis.close();
            return status;
        }

        /**
         * 添加对应关系，如果对应关系已存在，则覆盖
         *
         * @param key
         * @param map 对应关系
         * @return 状态，成功返回OK
         */
        public String hmset(byte[] key, Map<byte[], byte[]> map) {
            Jedis jedis = getJedis();
            String s = jedis.hmset(key, map);
            jedis.close();
            return s;
        }
    }


}
