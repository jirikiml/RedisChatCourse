package cz.redis;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;


public interface IRedisHashes
{
    public boolean hSet(@Nonnull String hashName, String hashKey, Object value);

    public boolean hSet(@Nonnull String hashName, HashEntry entryToSet);

    public <T> T hGet(@Nonnull String hashName, @Nonnull String hashKey);

    public String hMSet(@Nonnull String hashName, HashEntry... entries);

    public List<HashEntry> hMGet(@Nonnull String hashName, String... hashKeys);

    public List<HashEntry> hGetAll(@Nonnull String hashName);

    public Set<String> hKeys(@Nonnull String hashName);

    public long hDel(@Nonnull String hashName, String... hashKeysToRemove);

}
