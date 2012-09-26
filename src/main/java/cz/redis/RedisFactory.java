package cz.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import cz.redis.jedis.RedisBasic;
import cz.redis.jedis.RedisConnection;
import cz.redis.jedis.RedisHashes;
import cz.redis.jedis.RedisList;
import cz.redis.jedis.RedisSet;
import cz.redis.jedis.RedisSortedSet;

// TODO maybe change me to builder pattern
public class RedisFactory
{
    private static JedisPool pool = new JedisPool(RedisConstatnts.REDIS_MASTER);
    private Jedis jedis;

    public RedisFactory()
    {
        jedis = pool.getResource();
    }

    public RedisFactory(Jedis jedis)
    {
        this.jedis = jedis;
    }

    public IRedis createJedisRedis()
    {
        IRedisBasic basic = new RedisBasic(jedis);
        IRedisHashes hashes = new RedisHashes(jedis);
        IRedisConnection conn = new RedisConnection(jedis);
        IRedisList list = new RedisList(jedis);
        IRedisSet set = new RedisSet(jedis);
        IRedisSortedSet sortedSet = new RedisSortedSet(jedis);
        IRedis ret = new Redis(basic, hashes, list, set, sortedSet, conn);
        return ret;
    }

    public IRedisBasic createJedisBasic()
    {
        IRedisBasic ret = new RedisBasic(jedis);
        return ret;
    }

    public IRedisHashes createJedisHashes()
    {
        IRedisHashes ret = new RedisHashes(jedis);
        return ret;
    }

    public IRedisConnection createJedisConnection()
    {
        IRedisConnection ret = new RedisConnection(jedis);
        return ret;
    }

    public IRedisSortedSet createJedisSortedSet()
    {
        IRedisSortedSet ret = new RedisSortedSet(jedis);
        return ret;
    }

    public IRedisSet createJedisSet()
    {
        IRedisSet ret = new RedisSet(jedis);
        return ret;
    }

    public IRedisList createJedisList()
    {
        IRedisList ret = new RedisList(jedis);
        return ret;
    }

    public void returnResource()
    {
        pool.returnResource(jedis);
    }
}
