package cz.redis;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.commons.lang.Validate;

import redis.clients.jedis.Tuple;

public class Redis
        implements IRedis
{
    private IRedisBasic redisBasic;
    private IRedisHashes redisHashes;
    private IRedisConnection redisConnection;
    private IRedisSet redisSet;
    private IRedisSortedSet redisSortedSet;

    public Redis(@Nonnull
    IRedisBasic redisBasic, @Nonnull
    IRedisHashes redisHashes, @Nonnull
    IRedisSet redisSet, @Nonnull
    IRedisSortedSet redisSortedSet, @Nonnull
    IRedisConnection redisConnection)
    {
        Validate.notNull(redisBasic);
        Validate.notNull(redisHashes);
        Validate.notNull(redisSet);
        Validate.notNull(redisSortedSet);
        Validate.notNull(redisConnection);
        this.redisBasic = redisBasic;
        this.redisHashes = redisHashes;
        this.redisSet = redisSet;
        this.redisSortedSet = redisSortedSet;
        this.redisConnection = redisConnection;
    }

    @Override
    public String selectDB(int dbNumber)
    {
        return redisConnection.selectDB(dbNumber);
    }

    @Override
    public String set(String key, Object value)
    {
        return redisBasic.set(key, value);
    }

    @Override
    public <T> T get(String key)
    {
        return redisBasic.get(key);
    }

    @Override
    public long strlen(String key)
    {
        return redisBasic.strlen(key);
    }

    @Override
    public String getRange(String key, int start, int end)
    {
        return redisBasic.getRange(key, start, end);
    }

    @Override
    public int append(String key, String valueToAppend)
    {
        return redisBasic.append(key, valueToAppend);
    }

    @Override
    public long incr(String counter)
    {
        return redisBasic.incr(counter);
    }

    @Override
    public long incrBy(String counter, long valueToAdd)
    {
        return redisBasic.incrBy(counter, valueToAdd);
    }

    @Override
    public String flushDB()
    {
        return redisConnection.flushDB();
    }

    @Override
    public boolean hSet(String hashName, String hashKey, Object value)
    {
        return redisHashes.hSet(hashName, hashKey, value);
    }

    @Override
    public boolean hSet(String hashName, HashEntry entryToSet)
    {
        return redisHashes.hSet(hashName, entryToSet);
    }

    @Override
    public <T> T hGet(String hashName, String hashKey)
    {
        return redisHashes.hGet(hashName, hashKey);
    }

    @Override
    public String hMSet(String hashName, HashEntry... entries)
    {
        return redisHashes.hMSet(hashName, entries);
    }

    @Override
    public List<HashEntry> hMGet(String hashName, String... hashKeys)
    {
        return redisHashes.hMGet(hashName, hashKeys);
    }

    @Override
    public List<HashEntry> hGetAll(String hashName)
    {
        return redisHashes.hGetAll(hashName);
    }

    @Override
    public Set<String> hKeys(String hashName)
    {
        return redisHashes.hKeys(hashName);
    }

    @Override
    public long hDel(String hashName, String... hashKeysToRemove)
    {
        return redisHashes.hDel(hashName, hashKeysToRemove);
    }

    @Override
    public void quit()
    {
        redisConnection.quit();
    }

    @Override
    public long publish(String channel, String message)
    {
        return redisConnection.publish(channel, message);
    }

    @Override
    public void subscribe(String channel, IPubSubListener listener)
    {
        redisConnection.subscribe(channel, listener);
    }

    @Override
    public void unSubscribe(String channel)
    {
        redisConnection.unSubscribe(channel);
    }

    @Override
    public Long zadd(String key, double score, String member)
    {
        return redisSortedSet.zadd(key, score, member);
    }

    @Override
    public Double zincrby(String key, double score, String member)
    {
        return redisSortedSet.zincrby(key, score, member);
    }

    @Override
    public Set<Tuple> zrange(String key, long start, long end)
    {
        return redisSortedSet.zrange(key, start, end);
    }

    @Override
    public Set<Tuple> zrevrange(String key, long start, long end)
    {
        return redisSortedSet.zrevrange(key, start, end);
    }

    @Override
    public Double zscore(String key, String member)
    {
        return redisSortedSet.zscore(key, member);
    }

    @Override
    public Long sadd(String key, String... member)
    {
        return redisSet.sadd(key, member);
    }

    @Override
    public Set<String> smembers(String key)
    {
        return redisSet.smembers(key);
    }

    @Override
    public Long srem(String key, String... member)
    {
        return redisSet.srem(key, member);
    }

    @Override
    public Boolean sismember(String key, String member)
    {
        return redisSet.sismember(key, member);
    }
}
