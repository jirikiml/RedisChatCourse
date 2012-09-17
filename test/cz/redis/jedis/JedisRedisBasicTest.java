package cz.redis.jedis;

import org.junit.Before;

import cz.redis.RedisBasicTest;
import cz.redis.RedisFactory;

public class JedisRedisBasicTest
        extends RedisBasicTest
{
    private static int TEST_REDIS_DB = 1;

    @Override
    @Before
    public void setUp()
    {
        RedisFactory factory = new RedisFactory();
        redis = factory.createJedisBasic();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
    }

}
