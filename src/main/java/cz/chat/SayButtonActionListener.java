package cz.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import cz.chat.api.IChatClient;

public class SayButtonActionListener
        implements ActionListener
{
    private javax.swing.JTextField jInputField;
    private IChatClient client;
    private String channel;

    public SayButtonActionListener(JTextField jInputField, IChatClient client, String channel)
    {
        this.jInputField = jInputField;
        this.client = client;
        this.channel = channel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String message = jInputField.getText();
        this.jInputField.setText("");
        this.client.sendMessage(channel, message);
    }
}
