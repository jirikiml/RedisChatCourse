package cz.redis.jedis;

import org.junit.Before;

import cz.redis.IRedisConnection;
import cz.redis.RedisFactory;
import cz.redis.RedisHashesTest;

public class JedisRedisHashesTest
        extends RedisHashesTest
{
    private IRedisConnection redisConnection;
    private static int TEST_REDIS_DB = 1;

    @Override
    @Before
    public void setUp()
    {
        RedisFactory factory = new RedisFactory();
        redis = factory.createJedisHashes();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
    }

}
