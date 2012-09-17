package cz.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ChatMenuListener
        implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem source = (JMenuItem)(e.getSource());
        ChatMenuActions action = ChatMenuActions.valueOf(source.getName());
        System.out.println(action);
    }
}
