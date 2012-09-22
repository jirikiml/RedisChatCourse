package cz.redis;

import java.util.List;

public interface IRedisList
{
    public Long rpush(String key, String... strings);

    public Long lpush(String key, String... strings);

    public Long llen(String key);

    public List<String> lrange(String key, long start, long end);

    public String ltrim(String key, long start, long end);

    public String lindex(String key, long index);

}
