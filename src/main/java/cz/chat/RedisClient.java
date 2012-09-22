package cz.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.Validate;

import redis.clients.jedis.Tuple;
import cz.redis.IPubSubListener;
import cz.redis.IRedis;
import cz.redis.RedisFactory;

public class RedisClient
        implements IChatClient
{
    private IRedis redis;
    private String login;
    private static final String USERS_BY_NUMBER_OF_MESSAGES = "users:by:number:messages";
    private static final String USERS_BY_TIME_OF_LAST_MESSAGE = "users:by:time:of:last:message";
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
        redis.zadd(USERS_BY_TIME_OF_LAST_MESSAGE, System.currentTimeMillis(), login);
        redis.publish(channel, message);
    }

    @Override
    public void subscribe(String channel, IPubSubListener listener)
    {
        redis.zincrby(CHANNELS, 1, channel);
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
        redis.zincrby(CHANNELS, -1, channel);
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
    public int getNumberOfMessages(String usr)
    {
        Double ret = redis.zscore(USERS_BY_NUMBER_OF_MESSAGES, usr);
        return ret.intValue();
    }

    @Override
    public String getAllChannels()
    {
        Set<Tuple> allChannels = redis.zrevrange(CHANNELS, 0, -1);
        StringBuilder ret = new StringBuilder(CHANNELS);
        for (Tuple t : allChannels)
        {
            ret.append(" ").append(t.getElement()).append("(").append(t.getScore()).append(")\n");
        }
        return ret.toString();
    }

    @Override
    public String getUsersByMessageTime()
    {
        Set<Tuple> allUsers = redis.zrevrange(USERS_BY_TIME_OF_LAST_MESSAGE, 0, -1);
        StringBuilder ret = new StringBuilder(USERS_BY_NUMBER_OF_MESSAGES);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        for (Tuple t : allUsers)
        {
            long date = (long)t.getScore();
             
            Date resultdate = new Date(date);
            ret.append(" ").append(t.getElement()).append("(").append(sdf.format(resultdate)).append(")\n");
        }
        return ret.toString();
    }
}
