package cz.redis;

import javax.annotation.Nonnull;

import lombok.Delegate;

import org.apache.commons.lang.Validate;

public class Redis
        implements IRedis
{
    @Delegate
    private IRedisBasic redisBasic;
    @Delegate
    private IRedisHashes redisHashes;
    @Delegate
    private IRedisConnection redisConnection;
    @Delegate
    private IRedisList redisList;
    @Delegate
    private IRedisSet redisSet;
    @Delegate
    private IRedisSortedSet redisSortedSet;

    public Redis(@Nonnull
    IRedisBasic redisBasic, @Nonnull
    IRedisHashes redisHashes, @Nonnull
    IRedisList redisList, @Nonnull
    IRedisSet redisSet, @Nonnull
    IRedisSortedSet redisSortedSet, @Nonnull
    IRedisConnection redisConnection)
    {
        Validate.notNull(redisBasic);
        Validate.notNull(redisHashes);
        Validate.notNull(redisList);
        Validate.notNull(redisSet);
        Validate.notNull(redisSortedSet);
        Validate.notNull(redisConnection);
        this.redisBasic = redisBasic;
        this.redisHashes = redisHashes;
        this.redisList = redisList;
        this.redisSet = redisSet;
        this.redisSortedSet = redisSortedSet;
        this.redisConnection = redisConnection;
    }
}
