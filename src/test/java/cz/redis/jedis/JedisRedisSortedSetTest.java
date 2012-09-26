package cz.redis.jedis;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Tuple;
import cz.redis.IRedisSortedSet;

public class JedisRedisSortedSetTest
        extends AbstractJedisRedisTest
{
    private final String MY_SORTED_SET = PREFIX + "mySortedSet";
    private IRedisSortedSet redisSortedSet;

    @Override
    @Before
    public void setUp()
    {
        super.setUp();
        redisSortedSet = factory.createJedisSortedSet();
        long retVal = redisSortedSet.zadd(MY_SORTED_SET, 10, "ten");
        Assert.assertEquals(1, retVal);
        redisSortedSet.zadd(MY_SORTED_SET, 30, "thirty");
        redisSortedSet.zadd(MY_SORTED_SET, 20, "twenty");
    }

    @Test
    public void testZAddUpdate()
    {
        long retVal = redisSortedSet.zadd(MY_SORTED_SET, 10, "ten");
        Assert.assertEquals(0, retVal);
    }

    @Test
    public void testZRange()
    {
        Set<Tuple> ret = redisSortedSet.zrange(MY_SORTED_SET, 1, 1);
        Assert.assertEquals(1, ret.size());
        Tuple member = ret.iterator().next();
        Assert.assertEquals(20d, member.getScore());
        Assert.assertEquals("twenty", member.getElement());

        ret = redisSortedSet.zrange(MY_SORTED_SET, 0, -1);
        Assert.assertEquals(3, ret.size());
        member = ret.iterator().next();
        Assert.assertEquals(10d, member.getScore());
        Assert.assertEquals("ten", member.getElement());

        ret = redisSortedSet.zrange(MY_SORTED_SET, 0, 10);
        Assert.assertEquals("there are only three elements in set", 3, ret.size());
        // Thread.sleep(generator.nextInt(30));

        ret = redisSortedSet.zrange(MY_SORTED_SET, 5, 10);
        Assert.assertEquals("only three element, 5 to 10 is out of range", 0, ret.size());
    }

    @Test
    public void testZRevRange()
    {
        Set<Tuple> ret = redisSortedSet.zrevrange(MY_SORTED_SET, 0, -1);
        Assert.assertEquals(3, ret.size());
        Tuple member = ret.iterator().next();
        Assert.assertEquals(30d, member.getScore());
        Assert.assertEquals("thirty", member.getElement());
    }

    @Test
    public void testZScoreAndIncr()
    {
        Double score = redisSortedSet.zscore(MY_SORTED_SET, "ten");
        Assert.assertEquals(10, score, 0.0);
        redisSortedSet.zincrby(MY_SORTED_SET, 1, "ten");
        score = redisSortedSet.zscore(MY_SORTED_SET, "ten");
        Assert.assertEquals(11, score, 0.0);
        redisSortedSet.zincrby(MY_SORTED_SET, 1, "two");
        score = redisSortedSet.zscore(MY_SORTED_SET, "two");
        Assert.assertEquals("incr for non existing key is allowed", 1, score, 0.0);
    }
}
