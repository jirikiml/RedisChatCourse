package cz.redis;

import java.util.Set;

public interface IRedisSet
{
    public Long sadd(String key, String... member);

    public Set<String> smembers(String key);

    public Long srem(String key, String... member);

    public Boolean sismember(String key, String member);
}
