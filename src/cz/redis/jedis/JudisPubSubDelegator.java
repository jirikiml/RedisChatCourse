package cz.redis.jedis;

import redis.clients.jedis.JedisPubSub;
import cz.redis.IPubSubListener;


public class JudisPubSubDelegator
        extends JedisPubSub
{
    private IPubSubListener delegator;

    public JudisPubSubDelegator(IPubSubListener listener) {
        this.delegator = listener;
    }

    @Override
    public void onMessage(String channel, String message)
    {
        delegator.onMessage(channel, message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message)
    {
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels)
    {
        delegator.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels)
    {
        delegator.onUnsubscribe(channel, subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels)
    {
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels)
    {
    }

}
