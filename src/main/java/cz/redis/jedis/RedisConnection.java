package cz.redis.jedis;

import redis.clients.jedis.Jedis;
import cz.redis.IPubSubListener;
import cz.redis.IRedisConnection;

public class RedisConnection
        implements IRedisConnection
{

    private JudisPubSubDelegator pubSubDelegator;
    private Jedis jedis;

    public RedisConnection(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public String selectDB(int dbNumber)
    {
        String ret = jedis.select(dbNumber);
        return ret;
    }

    @Override
    public String flushDB()
    {
        String ret = jedis.flushDB();
        return ret;
    }

    @Override
    public void quit()
    {
        jedis.quit();
    }

    @Override
    public long publish(String channel, String message)
    {
        long ret = jedis.publish(channel, message);
        return ret;
    }

    @Override
    public void subscribe(final String channel, final IPubSubListener listener)
    {
        unSubscribe(channel);
        // I have to use another jedis connection for it, see manual pages of
        // subscribe
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                Jedis subJedis = new Jedis(jedis.getClient().getHost(), jedis.getClient().getPort());
                pubSubDelegator = new JudisPubSubDelegator(listener);
                subJedis.subscribe(pubSubDelegator, channel);

            }
        }).start();
    }

    @Override
    public void unSubscribe(String channel)
    {
        if (pubSubDelegator != null)
        {
            pubSubDelegator.unsubscribe(channel);
            // TODO quit on jedis ? is it needed ?
        }
    }

}
