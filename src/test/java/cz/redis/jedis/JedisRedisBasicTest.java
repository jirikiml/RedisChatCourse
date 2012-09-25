package cz.redis.jedis;

import org.junit.After;
import org.junit.Before;

import redis.clients.jedis.Jedis;
import cz.redis.RedisBasicTest;
import cz.redis.RedisFactory;

public class JedisRedisBasicTest
        extends RedisBasicTest
{
    private static int TEST_REDIS_DB = 1;
    private RedisFactory factory;

    @Override
    @Before
    public void setUp()
    {
        Jedis jedis = new Jedis("localhost");
        factory = new RedisFactory(jedis);
        redis = factory.createJedisBasic();
        redisConnection = factory.createJedisConnection();
        redisConnection.selectDB(TEST_REDIS_DB);
    }

    @After
    public void tearDown()
    {
        redisConnection.quit();
    }

}
