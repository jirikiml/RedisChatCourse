package cz.redis.jredis;

import org.apache.commons.lang.NotImplementedException;
import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.ri.alphazero.JRedisClient;
import org.jredis.ri.alphazero.support.DefaultCodec;

import cz.redis.IRedisBasic;
import cz.redis.RedisConstatnts;

public class JRedisBasic
        implements IRedisBasic
{
    private JRedis jRedis;

    public JRedisBasic()
    {
        jRedis = new JRedisClient(RedisConstatnts.REDIS_MASTER, RedisConstatnts.REDIS_MASTER_PORT);
    }

    @Override
    public String set(String key, Object value)
    {
        try
        {
            jRedis.set(key, (String)value);
        }
        catch (RedisException e)
        {
            throw new JRedisRuntimeException(e);
        }
        return "OK";
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key)
    {
        // TODO handle other types
        String ret;
        try
        {
            ret = DefaultCodec.toStr(jRedis.get(key));
        }
        catch (RedisException e)
        {
            throw new JRedisRuntimeException(e);
        }
        return (T)ret;
    }

    @Override
    public long strlen(String key)
    {
        // TODO realy strlen does NOT exists
        String retVal = get(key);
        long ret = (retVal == null)
                                   ? 0 : retVal.length();
        return ret;
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
        long ret;
        try
        {
            ret = jRedis.incr(counter);
        }
        catch (RedisException e)
        {
            throw new JRedisRuntimeException(e);
        }
        return ret;
    }

    @Override
    public long incrBy(String counter, long valueToAdd)
    {
        long ret;
        try
        {
            ret = jRedis.incrby(counter, (int)valueToAdd);
        }
        catch (RedisException e)
        {
            throw new JRedisRuntimeException(e);
        }
        return ret;
    }
}
