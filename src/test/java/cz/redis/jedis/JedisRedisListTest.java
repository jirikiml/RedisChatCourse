package cz.redis.jedis;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cz.redis.IRedisList;

public class JedisRedisListTest
        extends AbstractJedisRedisTest
{
    private static final String THREE = "three";
    private static final String TWO = "two";
    private static final String ONE = "one";
    private final String MY_LIST = PREFIX + "my:list";
    private IRedisList redisList;

    @Override
    @Before
    public void setUp()
    {
        super.setUp();
        redisList = factory.createJedisList();
        long retVal = redisList.rpush(MY_LIST, ONE);
        Assert.assertEquals(1, retVal);
        redisList.rpush(MY_LIST, TWO);
        redisList.rpush(MY_LIST, THREE);
    }

    @Test
    public void testLLen()
    {
        long retVal = redisList.llen(MY_LIST);
        Assert.assertEquals(3, retVal);

    }

    @Test
    public void testLindex()
    {
        Assert.assertEquals(ONE, redisList.lindex(MY_LIST, 0));
        Assert.assertEquals(TWO, redisList.lindex(MY_LIST, 1));
        Assert.assertEquals(THREE, redisList.lindex(MY_LIST, 2));

    }

    @Test
    public void testRPush()
    {
        String expectedVal = "QQQ";
        redisList.rpush(MY_LIST, expectedVal);
        long listSize = redisList.llen(MY_LIST);
        String retVal = redisList.lindex(MY_LIST, listSize - 1);
        Assert.assertEquals(expectedVal + "should be last item in list", expectedVal, retVal);

    }

    @Test
    public void testLPush()
    {
        String expectedVal = "QQQ";
        redisList.lpush(MY_LIST, expectedVal);
        String retVal = redisList.lindex(MY_LIST, 0);
        Assert.assertEquals(expectedVal, retVal);

    }

    @Test
    public void testLrange()
    {
        List<String> retVal = redisList.lrange(MY_LIST, 0, 1);
        Assert.assertEquals(2, retVal.size());
        Assert.assertEquals(ONE, retVal.get(0));
        Assert.assertEquals(TWO, retVal.get(1));

    }

    @Test
    public void testLrangeReverse()
    {
        List<String> retVal = redisList.lrange(MY_LIST, -1, 3);
        Assert.assertEquals(1, retVal.size());
        Assert.assertEquals(THREE, retVal.get(0));

    }

    @Test
    public void testLrangeOutOfBounds()
    {
        List<String> retVal = redisList.lrange(MY_LIST, 10, 13);
        Assert.assertEquals(0, retVal.size());
    }

    @Test
    public void testLTrim()
    {
        redisList.ltrim(MY_LIST, 1, -2);
        Assert.assertEquals(Long.valueOf(1L), redisList.llen(MY_LIST));
        Assert.assertEquals(TWO, redisList.lindex(MY_LIST, 0));
    }
}
