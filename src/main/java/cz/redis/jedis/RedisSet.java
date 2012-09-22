package cz.redis.jedis;

import java.util.Set;

import redis.clients.jedis.Jedis;

import cz.redis.IRedisSet;

public class RedisSet
        implements IRedisSet
{
    private Jedis jedis;

    public RedisSet(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public Long sadd(String key, String... members)
    {
        return jedis.sadd(key, members);
    }

    @Override
    public Set<String> smembers(String key)
    {
        return jedis.smembers(key);
    }

    @Override
    public Long srem(String key, String... members)
    {
        return jedis.srem(key, members);
    }

    @Override
    public Boolean sismember(String key, String member)
    {
        return jedis.sismember(key, member);
    }

}
