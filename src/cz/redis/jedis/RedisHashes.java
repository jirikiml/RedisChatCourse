package cz.redis.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import cz.redis.HashEntry;
import cz.redis.IRedisHashes;

public class RedisHashes
        implements IRedisHashes
{

    private Jedis jedis;

    public RedisHashes(Jedis jedis)
    {
        this.jedis = jedis;
    }

    @Override
    public boolean hSet(String hashName, String hashKey, Object value)
    {
        // TODO casting
        Long jedisRet = jedis.hset(hashName, hashKey, (String)value);
        boolean ret = (jedisRet == 1);
        return ret;
    }

    @Override
    public boolean hSet(String hashName, HashEntry entryToSet)
    {
        return hSet(hashName, entryToSet.getKey(), entryToSet.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T hGet(String hashName, String hashKey)
    {
        String ret = jedis.hget(hashName, hashKey);
        return (T)ret;
    }

    @Override
    public String hMSet(String hashName, HashEntry... entries)
    {
        Map<String, String> entriesAsMap = new LinkedHashMap<>();
        for (HashEntry entry : entries)
        {
            entriesAsMap.put(entry.getKey(), (String)entry.getValue());
        }
        String ret = jedis.hmset(hashName, entriesAsMap);
        return ret;
    }

    @Override
    public List<HashEntry> hMGet(String hashName, String... hashKeys)
    {
        List<String> jedisRet = jedis.hmget(hashName, hashKeys);
        List<HashEntry> ret = mapToHashEntriesList(jedisRet, hashKeys);
        return ret;
    }

    @Override
    public List<HashEntry> hGetAll(String hashName)
    {
        Map<String, String> jedisRet = jedis.hgetAll(hashName);
        List<HashEntry> ret = mapToHashEntriesList(jedisRet.values(),
                jedisRet.keySet().toArray(new String[0]));
        return ret;
    }

    @Override
    public Set<String> hKeys(String hashName)
    {
        Set<String> ret = jedis.hkeys(hashName);
        return ret;
    }

    @Override
    public long hDel(String hashName, String... hashKeysToRemove)
    {
        Long ret = jedis.hdel(hashName, hashKeysToRemove);
        return ret;
    }

    private List<HashEntry> mapToHashEntriesList(Collection<String> jedisRet, String... hashKeys)
    {
        int size = jedisRet.size();
        List<HashEntry> ret = new ArrayList<>(size);
        Iterator<String> iter = jedisRet.iterator();
        for (int i = 0; i < size; i++)
        {
            String value = iter.next();
            ret.add(new HashEntry(hashKeys[i], value));
        }
        return ret;
    }
}
