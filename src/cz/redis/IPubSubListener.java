package cz.redis;

public interface IPubSubListener
{
    public void onMessage(String channel, String message);

    public void onSubscribe(String channel, int subscribedChannels);

    public void onUnsubscribe(String channel, int subscribedChannels);

}
