package cz.redis.jedis;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;

import redis.clients.jedis.Jedis;
import cz.redis.IRedisBasic;

public class RedisBasic
        implements IRedisBasic
{
    private Jedis jedis;

    public RedisBasic(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public String set(String key, Object value)
    {
        String ret = jedis.set(key, (String)value);
        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key)
    {
        // TODO handle other types
        String ret = jedis.get(key);
        return (T)ret;
    }

    @Override
    public long strlen(String key)
    {
        Validate.notNull(key);
        Long ret = jedis.strlen(key);
        return ret.intValue();
    }

    @Override
    public String getRange(String key, int start, int end)
    {
        throw new NotImplementedException();
    }

    @Override
    public int append(String key, String valueToAppend)
    {
        throw new NotImplementedException();
    }

    @Override
    public long incr(String counter)
    {
        Long ret = jedis.incr(counter);
        return ret;
    }

    @Override
    public long incrBy(String counter, long valueToAdd)
    {
        Long ret = jedis.incrBy(counter, valueToAdd);
        return ret;
    }

}
