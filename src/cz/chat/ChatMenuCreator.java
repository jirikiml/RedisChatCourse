package cz.chat;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChatMenuCreator
{
    private ActionListener listener;

    public ChatMenuCreator(ActionListener listener)
    {
        this.listener = listener;
    }

    public JMenuBar createMenu()
    {
        JMenuBar ret = new JMenuBar();
        JMenu menu = new JMenu("A Menu");
        ret.add(menu);

        JMenuItem menuItem = createMenuItem(ChatMenuActions.ALL_CHANNELS);
        menu.add(menuItem);
        menuItem = createMenuItem(ChatMenuActions.ALL_CONNECTED_USERS);
        menu.add(menuItem);
        menuItem = createMenuItem(ChatMenuActions.LAST_TEN_MESSAGES);
        menu.add(menuItem);
        menuItem = createMenuItem(ChatMenuActions.USER_BY_DISCONNECT_TIME);
        menu.add(menuItem);

        menuItem = createMenuItem(ChatMenuActions.USERS_BY_ACTIVITY);
        menu.add(menuItem);

        menu.addSeparator();
        JMenuItem quitItem = createMenuItem(ChatMenuActions.QUIT);
        menu.add(quitItem);
        return ret;
    }

    public JMenuItem createMenuItem(ChatMenuActions action)
    {
        JMenuItem menuItem = new JMenuItem(action.getText());
        menuItem.setName(action.name());
        menuItem.addActionListener(listener);
        return menuItem;
    }

}
