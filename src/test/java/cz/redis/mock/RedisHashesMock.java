package cz.redis.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;

import cz.redis.HashEntry;
import cz.redis.IRedisBasic;
import cz.redis.IRedisHashes;

public class RedisHashesMock
        implements IRedisHashes
{
    private IRedisBasic redisBasic;

    public RedisHashesMock()
    {
        redisBasic = new RedisBasicMock();
    }

    @Override
    public boolean hSet(String hashName, String hashKey, Object value)
    {
        Validate.notNull(hashName);
        Validate.notNull(hashKey);
        Map<String, Object> mapToSet = redisBasic.get(hashName);
        if (mapToSet == null)
        {
            mapToSet = new HashMap<String, Object>();
        }
        Object putRet = mapToSet.put(hashKey, value);
        boolean ret = (putRet == null);
        redisBasic.set(hashName, mapToSet);
        return ret;
    }

    @Override
    public boolean hSet(String hashName, HashEntry entryToSet)
    {
        Validate.notNull(hashName);
        boolean ret = hSet(hashName, entryToSet.getKey(), entryToSet.getValue());
        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T hGet(String hashName, String hashKey)
    {
        Validate.notNull(hashName);
        Validate.notNull(hashKey);
        T ret;
        Map<String, Object> value = redisBasic.get(hashName);
        if (value == null)
        {
            ret = null;
        }
        else
        {
            ret = (T)value.get(hashKey);
        }
        return ret;
    }

    @Override
    public String hMSet(String hashName, HashEntry... entries)
    {
        Validate.notNull(hashName);
        for (HashEntry entryToSet : entries)
        {
            hSet(hashName, entryToSet);
        }
        return "OK";
    }

    @Override
    public List<HashEntry> hMGet(String hashName, String... hashKeys)
    {
        Validate.notNull(hashName);
        List<HashEntry> ret = new ArrayList<HashEntry>();
        for (String key : hashKeys)
        {
            Object value = hGet(hashName, key);
            ret.add(new HashEntry(key, value));
        }
        return ret;
    }

    @Override
    public Set<String> hKeys(String hashName)
    {
        Validate.notNull(hashName);
        Map<String, Object> map = redisBasic.get(hashName);
        Set<String> ret = map.keySet();
        return new LinkedHashSet<String>(ret);
    }

    @Override
    public List<HashEntry> hGetAll(String hashName)
    {
        Validate.notNull(hashName);
        Set<String> keys = hKeys(hashName);
        List<HashEntry> ret = hMGet(hashName, keys.toArray(new String[0]));
        return ret;
    }

    @Override
    public long hDel(String hashName, String... hashKeysToRemove)
    {
        Validate.notNull(hashName);
        int ret = 0;
        Map<String, Object> value = redisBasic.get(hashName);
        for (String keyToRemove : hashKeysToRemove)
        {
            if (value.remove(keyToRemove) != null)
            {
                ret++;
            }
        }
        return ret;
    }

}
