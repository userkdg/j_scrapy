package cn.kindg.jscrapy;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClientTest {
    static {
        init();
    }
    private static void init(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool("localhost", 6379);
    }

    private static class Holder {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        private static JedisPool jedisPool = new JedisPool("localhost", 6379);
    }

    public static JedisPool getJedisPoolInstance(){
        return Holder.jedisPool;
    }

    public static void main(String[] args) {
        try (Jedis jedis = getJedisPoolInstance().getResource()) {
            jedis.select(1);// 0 -> ..15
            String valu = jedis.get("test:redis:key");
            System.out.println(valu);

            String set = jedis.set("test:redis:key", String.valueOf(111));
            System.out.println(set);
            valu = jedis.get("test:redis:key");
            System.out.println(valu);

            String key = "test:redis:ttl:-1:key";
            String setex = jedis.set(key, "不过期");
            Long persist = jedis.persist(key);
            System.out.println(persist);
            System.out.println(setex);
            System.out.println(jedis.get(key));
        }
    }
}
