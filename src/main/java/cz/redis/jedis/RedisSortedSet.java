package cz.redis.jedis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import cz.redis.IRedisSortedSet;

public class RedisSortedSet
        implements IRedisSortedSet
{

    private Jedis jedis;

    public RedisSortedSet(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public Long zadd(String key, double score, String member)
    {
        return jedis.zadd(key, score, member);
    }

    @Override
    public Set<Tuple> zrange(String key, long start, long end)
    {
        return jedis.zrangeWithScores(key, start, end);
    }

    @Override
    public Double zincrby(String key, double score, String member)
    {
        return jedis.zincrby(key, score, member);
    }

    @Override
    public Double zscore(String key, String member)
    {
        return jedis.zscore(key, member);
    }

    @Override
    public Set<Tuple> zrevrange(String key, long start, long end)
    {
        return jedis.zrevrangeWithScores(key, start, end);
    }
}
