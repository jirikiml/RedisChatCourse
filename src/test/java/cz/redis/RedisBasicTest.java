package cz.redis;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cz.redis.mock.RedisBasicMock;

public class RedisBasicTest
{
    protected IRedisBasic redis;
    protected IRedisConnection redisConnection;
    protected final String PREFIX = "" + Thread.currentThread().getId() + System.nanoTime();

    @Before
    public void setUp()
    {
        redis = new RedisBasicMock();
    }

    @Test
    public void testGetSet()
    {
        System.out.println(RedisConstatnts.REDIS_MASTER);
        String key = PREFIX + "myKey";
        String value = "myValue";
        redis.set(key, value);
        String retVal = redis.get(key);
        Assert.assertEquals(value, retVal);
    }

    @Test
    public void testStrlen()
    {
        String key = PREFIX + "myKey";
        String value = "myValue";
        redis.set(key, value);
        long retVal = redis.strlen(key);
        Assert.assertEquals(value.length(), retVal);
    }

    @Test
    public void testStrlenKeyDoesNotExists()
    {
        long retVal = redis.strlen("does:NOT:exists");
        Assert.assertEquals(0, retVal);
    }

    @Test
    public void testIncr()
    {
        String counter = PREFIX + "myCounter";
        long retVal = redis.incr(counter);
        Assert.assertEquals(1, retVal);
        retVal = redis.incr(counter);
        Assert.assertEquals(2, retVal);
    }

    @Test
    public void testIncrBy()
    {
        String counter = PREFIX + "IncrByCounter";
        long retVal = redis.incrBy(counter, 4);
        Assert.assertEquals(counter, 4, retVal);
        retVal = redis.incrBy(counter, 2);
        Assert.assertEquals(counter, 6, retVal);
    }
}
