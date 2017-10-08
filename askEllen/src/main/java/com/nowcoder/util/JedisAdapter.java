package com.nowcoder.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis使用测试
 */
@Service
public class JedisAdapter implements InitializingBean {
    private JedisPool jedisPool;

    public static void print(int index, Object obj) {
        System.out.println(String.format("%d,%s", index, obj.toString()));
    }

//    public static void main(String[] args){
//        //默认使用6379
//
//        Jedis jedis = new Jedis("redis://localhost:6379/12");
////
////        //get set操作
////        jedis.set("hello","world");
////        System.out.println(jedis.get("hello"));
////
////        jedis.rename("hello","newhello");
////        System.out.println(jedis.get("newhello"));
////
////        //10s后自动销毁
////        jedis.setex("hello2",10,"10second");
////        System.out.println(jedis.get("hello2"));
//
//        //数值操作 increBy decreBy
////        jedis.set("pv","100");
////        jedis.incr("pv");
////        jedis.incrBy("pv",5);
////
////        System.out.println(jedis.keys("*"));
////
////        jedis.flushDB();
////        System.out.println(jedis.keys("*"));
//
//        //List
////        String listName = "list";
////        jedis.del(listName);
////        for(int i=0;i<10;i++){
////            jedis.lpush(listName,"a"+String.valueOf(i));
////        }
////
////        print(4,jedis.lrange(listName,0,12));
////        print(4,jedis.lrange(listName,0,3));
////        print(6,jedis.llen(listName));
////        print(7, jedis.lpop(listName));
////        print(8,jedis.lrange(listName,0,10));
////
////        //hash
////        String user = "userxx";
////        jedis.hset(user,"name","jim");
////        jedis.hset(user,"age","12");
////        jedis.hset(user,"phone","123123123");
////        print(12,jedis.hget(user,"name"));
////        print(13, jedis.hgetAll(user));
//
////        String likeKey1 = "commentLike1";
////        String likeKey2 = "commentLike2";
////        for(int i=0;i<10;i++){
////            jedis.sadd(likeKey1, String.valueOf(i));
////            jedis.sadd(likeKey2, String.valueOf(i * i));
////        }
////
////        print(1, jedis.smembers(likeKey1));
////        print(2,jedis.smembers(likeKey2));
////        print(3,jedis.sunion(likeKey1,likeKey2));
//
//        User user = new User();
//        user.setName("name");
//        jedis.set("user", JSONObject.toJSONString(user));
//
//        String value = jedis.get("user");
//        print(1, value);
//        User user2 = JSON.parseObject(value, User.class);
//        print(2,user2);
//    }


    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("redis://localhost:6379/12");

    }

    public long sadd(String key,String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long srem(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public boolean sismember(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}
