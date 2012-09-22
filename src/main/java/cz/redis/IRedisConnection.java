package cz.redis;

public interface IRedisConnection
{
    public String selectDB(int dbNumber);

    public String flushDB();

    public void quit();

    public long publish(String channel, String message);

    public void subscribe(String channel, IPubSubListener listener);

    public void unSubscribe(String channel);

}
