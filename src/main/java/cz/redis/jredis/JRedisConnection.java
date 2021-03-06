package cz.redis.jredis;

import org.jredis.RedisException;
import org.jredis.ri.alphazero.JRedisClient;

import cz.redis.IPubSubListener;
import cz.redis.IRedisConnection;
import cz.redis.RedisConstatnts;

public class JRedisConnection
        implements IRedisConnection
{

    private JRedisClient jRedis;

    public JRedisConnection()
    {
        jRedis = new JRedisClient(RedisConstatnts.REDIS_MASTER, RedisConstatnts.REDIS_MASTER_PORT);
    }

    @Override
    public String selectDB(int dbNumber)
    {
        return null;
    }

    @Override
    public String flushDB()
    {
        try
        {
            jRedis.flushdb();
        }
        catch (RedisException e)
        {
            throw new JRedisRuntimeException(e);
        }
        return "OK";
    }

    @Override
    public void quit()
    {
        jRedis.quit();

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
