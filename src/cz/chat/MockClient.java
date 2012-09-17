package cz.chat;

import cz.redis.IPubSubListener;

public class MockClient
        implements IChatClient
{

    @Override
    public void sendMessage(String channel, String message)
    {
        System.out.println("message sended");
    }

    @Override
    public void connect(String string, int i)
    {
        System.out.println("connected");
    }

    @Override
    public void disconnect()
    {
        System.out.println("disconnected");

    }

    @Override
    public void selectDB(int i)
    {
        System.out.println("db selected");
        
    }

    @Override
    public void subscribe(String channel, IPubSubListener listener)
    {
        System.out.println("sub");
        
    }

    @Override
    public void unSubscribe(String channel)
    {
        System.out.println("unsub");
        
    }

    @Override
    public String getLogin()
    {
        return null;
    }

    @Override
    public void setLogin(String login)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getNumberOfMessages(String login)
    {
        return 1;
    }
}
