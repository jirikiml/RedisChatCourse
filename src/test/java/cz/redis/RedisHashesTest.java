package cz.redis;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cz.redis.mock.RedisHashesMock;

public class RedisHashesTest
{
    protected IRedisHashes redis;

    @Before
    public void setUp()
    {
        redis = new RedisHashesMock();
    }

    @Test
    public void testHGetSet()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        boolean isNew = redis.hSet(hashName, hashKey, "will be replaced");
        Assert.assertEquals("it is insert", true, isNew);
        isNew = redis.hSet(hashName, hashKey, value);
        Assert.assertEquals("it is update", false, isNew);
        String retVal = redis.hGet(hashName, hashKey);
        Assert.assertEquals(value, retVal);
        String hashKey2 = "email";
        String value2 = "pepa.zdepa@email.cz";
        redis.hSet(hashName, hashKey2, value2);
        retVal = redis.hGet(hashName, hashKey);
        Assert.assertEquals(value, retVal);
        retVal = redis.hGet(hashName, hashKey2);
        Assert.assertEquals(value2, retVal);
    }

    @Test
    public void testHGetKeyNotExists()
    {
        Object retVal = redis.hGet("does:NOT:exists", "bla");
        Assert.assertNull(retVal);
    }

    @Test
    public void testHGetHashNotExists()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        redis.hSet(hashName, hashKey, value);
        Object retVal = redis.hGet(hashName, "does:NOT:exists");
        Assert.assertNull(retVal);
    }

    @Test
    public void testHMGetHMSet()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        String hashKey2 = "email";
        String value2 = "pepa.zdepa@email.cz";
        HashEntry he1 = new HashEntry(hashKey, value);
        HashEntry he2 = new HashEntry(hashKey2, value2);
        String retValHMSet = redis.hMSet(hashName, he1, he2);
        Assert.assertEquals("OK", retValHMSet);
        List<HashEntry> retVal = redis.hMGet(hashName, hashKey, hashKey2);
        Assert.assertEquals(2, retVal.size());
        Assert.assertEquals(he1, retVal.get(0));
        Assert.assertEquals(he2, retVal.get(1));
    }

    @Test
    public void testHKeys()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        redis.hSet(hashName, hashKey, value);
        String hashKey2 = "email";
        String value2 = "pepa.zdepa@email.cz";
        redis.hSet(hashName, hashKey2, value2);
        Set<String> keys = redis.hKeys(hashName);
        Assert.assertTrue(keys.contains(hashKey));
        Assert.assertTrue(keys.contains(hashKey2));
    }

    @Test
    public void testHGetAll()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        String hashKey2 = "email";
        String value2 = "pepa.zdepa@email.cz";
        HashEntry he1 = new HashEntry(hashKey, value);
        HashEntry he2 = new HashEntry(hashKey2, value2);
        redis.hMSet(hashName, he1, he2);
        List<HashEntry> retVal = redis.hGetAll(hashName);
        Assert.assertEquals(2, retVal.size());
        Assert.assertTrue(retVal.contains(he1));
        Assert.assertTrue(retVal.contains(he2));
    }

    @Test
    public void testHDel()
    {
        String hashName = "users:pepa";
        String hashKey = "title";
        String value = "Mgr.";
        redis.hSet(hashName, hashKey, value);
        String hashKey2 = "email";
        String value2 = "pepa.zdepa@email.cz";
        redis.hSet(hashName, hashKey2, value2);
        long retVal = redis.hDel(hashName, hashKey, hashKey2);
        Assert.assertEquals(2, retVal);
        Set<String> keys = redis.hKeys(hashName);
        Assert.assertTrue("all should be deleted by hDel command above", keys.isEmpty());
    }
}
