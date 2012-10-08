package cz.redis.jedis;

import org.junit.After;
import org.junit.Before;

import cz.redis.IRedisConnection;
import cz.redis.RedisHashesTest;
import cz.redis.factory.RedisFactory;

public class JedisRedisHashesTest
        extends RedisHashesTest
{
    private IRedisConnection redisConnection;
    private RedisFactory factory;
    private static int TEST_REDIS_DB = 1;

    @Override
    @Before
    public void setUp()
    {
        factory = new RedisFactory();
        redis = factory.createJedisHashes();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
    }

    @After
    public void tearDown()
    {
        factory.returnResource();
    }

}
