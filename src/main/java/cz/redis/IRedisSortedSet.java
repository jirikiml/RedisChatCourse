package cz.redis;

import java.util.Set;

import redis.clients.jedis.Tuple;

public interface IRedisSortedSet
{
    public Long zadd(final String key, final double score, final String member);

    public Double zincrby(final String key, final double score, final String member);

    public Set<Tuple> zrange(final String key, final long start, final long end);

    public Set<Tuple> zrevrange(final String key, final long start, final long end);

    public Double zscore(final String key, final String member);

}
