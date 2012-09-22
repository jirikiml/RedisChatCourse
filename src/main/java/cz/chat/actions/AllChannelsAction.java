package cz.chat.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import cz.chat.IChatClient;

public class AllChannelsAction
        extends AbstractChatAction
{
    private static final long serialVersionUID = 7164956659112340755L;

    public AllChannelsAction(IChatClient client, JTextArea jChatArea)
    {
        super("all existing channels", client, jChatArea);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String channels = client.getAllChannels();
        jChatArea.append(channels + "\n");
    }

}
