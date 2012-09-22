package cz.redis;

import javax.annotation.Nonnull;

public interface IRedisBasic
{
    // TODO ?? maybe value should be at least serializable
    public String set(@Nonnull String key, Object value);

    public <T> T get(@Nonnull String key);

    public long strlen(@Nonnull String key);

    public String getRange(@Nonnull String key, int start, int end);

    public int append(@Nonnull String key, String valueToAppend);

    public long incr(@Nonnull String counter);

    public long incrBy(@Nonnull String counter, long valueToAdd);
    
}
