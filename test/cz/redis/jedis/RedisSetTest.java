package cz.redis.jedis;

import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.redis.IRedisConnection;
import cz.redis.IRedisSet;
import cz.redis.RedisFactory;

public class RedisSetTest
{
    private static final String THREE = "three";
    private static final String TWO = "two";
    private static final String ONE = "one";
    private static final String MY_SET = "my:set";
    private static int TEST_REDIS_DB = 1;
    private IRedisSet redisSet;
    private IRedisConnection redisConnection;

    @Before
    public void setUp()
    {
        RedisFactory factory = new RedisFactory();
        redisSet = factory.createJedisSet();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
        redisConnection.flushDB();
        long retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals(1, retVal);
        redisSet.sadd(MY_SET, TWO);
        redisSet.sadd(MY_SET, THREE);
    }

    @After
    public void tearDown()
    {
        // TODO check quit in other tests as well
        // maybe I should have one general class to handle such
        // connect/disconnet/flush
        redisConnection.quit();
    }

    @Test
    public void testSadd() {
        long retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals("item is has been added to set in setup already", 0, retVal);
        
    }

    @Test
    public void testSmembers() {
        Set<String> retVal = redisSet.smembers(MY_SET);
        Assert.assertEquals(3, retVal.size());
        Assert.assertTrue(retVal.contains(ONE));
        Assert.assertTrue(retVal.contains(TWO));
        Assert.assertTrue(retVal.contains(THREE));
    }

    @Test
    public void testSrem() {
        long retVal = redisSet.srem(MY_SET, ONE);
        Assert.assertEquals(1, retVal);
        retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals("should be again added", 1, retVal);
    }

    @Test
    public void testSismember() {
        Assert.assertTrue(redisSet.sismember(MY_SET, ONE));
        Assert.assertFalse(redisSet.sismember(MY_SET, "NO_ITEM"));
    }
}
