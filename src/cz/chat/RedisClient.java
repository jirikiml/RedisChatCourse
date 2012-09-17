package cz.chat;

import org.apache.commons.lang.Validate;

import cz.redis.IPubSubListener;
import cz.redis.IRedis;
import cz.redis.RedisFactory;

public class RedisClient
        implements IChatClient
{
    private IRedis redis;
    private String login;
    private static final String USERS_BY_NUMBER_OF_MESSAGES = "users:by:number:messages";
    private static final String CONNECTED_USERS = "users:connected";
    private static final String CHANNELS = "channels:all";


    @Override
    public void connect(String string, int port)
    {
        RedisFactory factory = new RedisFactory();
        redis = factory.createJedisRedis();
        // TODO add to list of connected users
    }

    @Override
    public void disconnect()
    {
        // TODO remove list of connected users
        redis.quit();
        redis = null;
    }

    @Override
    public void sendMessage(String channel, String message)
    {
        redis.zincrby(USERS_BY_NUMBER_OF_MESSAGES, 1, login);
        redis.publish(channel, message);
    }

    @Override
    public void subscribe(String channel, IPubSubListener listener)
    {
        redis.zadd(CHANNELS, 0, channel);
        redis.subscribe(channel, listener);
    }

    @Override
    public void selectDB(int dbNumber)
    {
        redis.selectDB(dbNumber);
    }

    @Override
    public void unSubscribe(String channel)
    {
        redis.unSubscribe(channel);
    }

    @Override
    public void setLogin(String login)
    {
        Validate.notNull(login);
        this.login = login;
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public int getNumberOfMessages(String login)
    {
        Double ret = redis.zscore(USERS_BY_NUMBER_OF_MESSAGES, login);
        return ret.intValue();
    }

}
