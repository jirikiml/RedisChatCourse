package cz.redis.jedis;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cz.redis.IRedisSet;

public class JedisRedisSetTest
        extends AbstractJedisRedisTest
{
    private static final String THREE = "three";
    private static final String TWO = "two";
    private static final String ONE = "one";
    private final String MY_SET = PREFIX + "my:set";
    private IRedisSet redisSet;

    @Override
    @Before
    public void setUp()
    {
        super.setUp();
        redisSet = factory.createJedisSet();
        long retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals(1, retVal);
        redisSet.sadd(MY_SET, TWO);
        redisSet.sadd(MY_SET, THREE);
    }

    @Test
    public void testSadd()
    {
        long retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals("item is has been added to set in setup already", 0, retVal);

    }

    @Test
    public void testSmembers()
    {
        Set<String> retVal = redisSet.smembers(MY_SET);
        Assert.assertEquals(3, retVal.size());
        Assert.assertTrue(retVal.contains(ONE));
        Assert.assertTrue(retVal.contains(TWO));
        Assert.assertTrue(retVal.contains(THREE));
    }

    @Test
    public void testSrem()
    {
        long retVal = redisSet.srem(MY_SET, ONE);
        Assert.assertEquals(1, retVal);
        retVal = redisSet.sadd(MY_SET, ONE);
        Assert.assertEquals("should be again added", 1, retVal);
    }

    @Test
    public void testSismember()
    {
        Assert.assertTrue(redisSet.sismember(MY_SET, ONE));
        Assert.assertFalse(redisSet.sismember(MY_SET, "NO_ITEM"));
    }
}
