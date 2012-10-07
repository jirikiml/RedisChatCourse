package cz.redis.jredis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.redis.RedisBasicTest;

public class JRedisRedisBasicTest
        extends RedisBasicTest
{
    private static int TEST_REDIS_DB = 1;

    @Override
    @Before
    public void setUp()
    {
        redis = new JRedisBasic();
        redisConnection = new JRedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
    }
    
    @Test
    public void testException() throws JRedisRuntimeException
    {
        JRedisRuntimeException ex = new JRedisRuntimeException(new NullPointerException());
        Assert.assertTrue(ex.getCause() instanceof NullPointerException);
    }

}
