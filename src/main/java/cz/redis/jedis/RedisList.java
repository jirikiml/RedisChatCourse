package cz.redis.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;

import cz.redis.IRedisList;

public class RedisList
        implements IRedisList
{
    private Jedis jedis;

    public RedisList(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public Long rpush(String key, String... strings)
    {
        Long ret = jedis.rpush(key, strings);
        return ret;
    }

    @Override
    public Long lpush(String key, String... strings)
    {
        Long ret = jedis.lpush(key, strings);
        return ret;
    }

    @Override
    public Long llen(String key)
    {
        Long ret = jedis.llen(key);
        return ret;
    }

    @Override
    public List<String> lrange(String key, long start, long end)
    {
        List<String> ret = jedis.lrange(key, start, end);
        return ret;
    }

    @Override
    public String ltrim(String key, long start, long end)
    {
        String ret = jedis.ltrim(key, start, end);
        return ret;
    }

    @Override
    public String lindex(String key, long index)
    {
        String ret = jedis.lindex(key, index);
        return ret;
    }

}
