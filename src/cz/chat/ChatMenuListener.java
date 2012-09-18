package cz.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class ChatMenuListener
        implements ActionListener
{

    private JTextArea jChatArea;
    private IChatClient client;
    public ChatMenuListener(IChatClient client, JTextArea jChatArea) {
        this.client = client;
        this.jChatArea = jChatArea;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem source = (JMenuItem)(e.getSource());
        ChatMenuActions action = ChatMenuActions.valueOf(source.getName());
        System.out.println(action);
        // TODO get rid of such swith -> use actions directly during menu creation
        switch (action)
        {
            case ALL_CHANNELS:
                String channels = client.getAllChannels();
                jChatArea.append(channels + "\n");
                break;

            default:
                break;
        }
    }
}
