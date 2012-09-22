package cz.chat.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import cz.chat.IChatClient;

public class UsersByLastMessageTimeAction
        extends AbstractChatAction
{
    private static final long serialVersionUID = 1L;

    public UsersByLastMessageTimeAction(IChatClient client, JTextArea jChatArea)
    {
        super("all connected users + last message time", client, jChatArea);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String users = client.getUsersByMessageTime();
        jChatArea.append(users + "\n");
    }

}
