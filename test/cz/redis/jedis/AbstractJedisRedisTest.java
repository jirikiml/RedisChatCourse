package cz.redis.jedis;

import org.junit.After;
import org.junit.Before;

import cz.redis.IRedisConnection;
import cz.redis.RedisFactory;

public abstract class AbstractJedisRedisTest
{
    protected static int TEST_REDIS_DB = 1;
    protected IRedisConnection redisConnection;
    protected RedisFactory factory;

    @Before
    public void setUp()
    {
        factory = new RedisFactory();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
    }

    @After
    public void tearDown()
    {
        redisConnection.quit();
    }

}