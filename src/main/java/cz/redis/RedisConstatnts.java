package cz.redis;

public class RedisConstatnts
{
    public static final String REDIS_MASTER = System.getProperty("REDIS_MASTER", "localhost");
    public static final int REDIS_MASTER_PORT = Integer.valueOf(System
            .getProperty("REDIS_MASTER_PORT", "6379"));
}
