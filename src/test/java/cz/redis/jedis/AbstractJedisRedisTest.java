package cz.redis.jedis;

import org.junit.After;
import org.junit.Before;

import redis.clients.jedis.Jedis;

import cz.redis.IRedisConnection;
import cz.redis.RedisConstatnts;
import cz.redis.RedisFactory;

public abstract class AbstractJedisRedisTest
{
    protected final String PREFIX = "" + Thread.currentThread().getId() + System.nanoTime();
    protected static int TEST_REDIS_DB = 1;
    protected IRedisConnection redisConnection;
    protected RedisFactory factory;

    @Before
    public void setUp()
    {
        Jedis jedis = new Jedis(RedisConstatnts.REDIS_MASTER);
        factory = new RedisFactory(jedis);
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
    }

    @After
    public void tearDown()
    {
        redisConnection.quit();
    }

}