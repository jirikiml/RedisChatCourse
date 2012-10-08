package cz.chat;

import javax.swing.JTextArea;

import cz.chat.api.IChatClient;
import cz.redis.IPubSubListener;

public class ChatPubSubListener
        implements IPubSubListener
{
    private static final String NL = "\n";
    private JTextArea jChatArea;
    private String login;
    private IChatClient client;

    public ChatPubSubListener(JTextArea jChatArea, IChatClient client)
    {
        this.jChatArea = jChatArea;
        this.client = client;
    }

    @Override
    public void onMessage(String channel, String message)
    {
        int numOfMessages = client.getNumberOfMessages(login);
        this.jChatArea.append(login + "(" + numOfMessages + "): " + message + NL);

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels)
    {
        this.jChatArea.append(login + " subscribed to: " + channel + NL);

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels)
    {
        this.jChatArea.append(login + " unsubscribed from " + channel + NL);

    }

    public void setLogin(String login)
    {
        this.login = login;
    }

}
