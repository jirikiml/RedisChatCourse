package cz.redis;

import redis.clients.jedis.Jedis;
import cz.redis.jedis.RedisBasic;
import cz.redis.jedis.RedisConnection;
import cz.redis.jedis.RedisHashes;
import cz.redis.jedis.RedisSet;
import cz.redis.jedis.RedisSortedSet;

// TODO maybe change me to builder pattern
public class RedisFactory
{
    private Jedis jedis;

    public RedisFactory()
    {
        jedis = new Jedis("localhost");
    }

    public IRedis createJedisRedis()
    {
        IRedisBasic basic = new RedisBasic(jedis);
        IRedisHashes hashes = new RedisHashes(jedis);
        IRedisConnection conn = new RedisConnection(jedis);
        IRedisSet set = new RedisSet(jedis);
        IRedisSortedSet sortedSet = new RedisSortedSet(jedis);
        IRedis ret = new Redis(basic, hashes, set, sortedSet, conn);
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

}
