package cz.chat.api;

import cz.redis.IPubSubListener;

public interface IChatClient
{
    public void connect(String string, int i);

    public void disconnect();

    public void selectDB(int i);

    public void sendMessage(String channel, String message);

    public void subscribe(String channel, IPubSubListener listener);

    public void unSubscribe(String channel);

    public void setLogin(String login);

    public abstract String getLogin();

    public int getNumberOfMessages(String login);

    public String getAllChannels();

    public String getUsersByMessageTime();

}