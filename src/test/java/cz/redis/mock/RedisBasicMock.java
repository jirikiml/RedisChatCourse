package cz.redis.mock;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

import cz.redis.IPubSubListener;
import cz.redis.IRedisBasic;
import cz.redis.IRedisConnection;

// TODO rename to String and move some commands to other mock
public class RedisBasicMock
        implements IRedisBasic, IRedisConnection
{
    private Map<String, Object> redisBasic;

    public RedisBasicMock()
    {
        redisBasic = new HashMap<>();
    }

    @Override
    public String set(String key, Object value)
    {
        Validate.notNull(key);
        redisBasic.put(key, value);
        return "OK";
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key)
    {
        Validate.notNull(key);
        Object ret = redisBasic.get(key);
        return (ret == null)
                            ? null : (T)ret;
    }

    @Override
    public long strlen(String key)
    {
        Validate.notNull(key);
        int ret = 0;
        String value = get(key);
        if (value != null)
        {
            ret = value.length();
        }
        return ret;
    }

    @Override
    public String getRange(String key, int start, int end)
    {
        return null;
    }

    @Override
    public int append(String key, String valueToAppend)
    {
        return 0;
    }

    @Override
    public long incr(String counter)
    {
        Validate.notNull(counter);
        long ret = incrBy(counter, 1);
        return ret;
    }

    @Override
    public long incrBy(String counter, long valueToAdd)
    {
        Validate.notNull(counter);
        long ret;
        Long value = get(counter);
        if (value == null)
        {
            set(counter, valueToAdd);
            ret = valueToAdd;
        }
        else
        {
            long newVal = value + valueToAdd;
            set(counter, newVal);
            ret = newVal;
        }
        return ret;
    }

    @Override
    public String flushDB()
    {
        redisBasic.clear();
        return "OK";
    }

    @Override
    public String selectDB(int dbNumber)
    {
        return null;
    }

    @Override
    public void quit()
    {
        // NOOP
    }

    @Override
    public long publish(String channel, String message)
    {
        return 0;
    }

    @Override
    public void subscribe(String channel, IPubSubListener listener)
    {
        // NOOP
    }

    @Override
    public void unSubscribe(String channel)
    {
        // NOOP
    }
}
