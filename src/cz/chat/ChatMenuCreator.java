package cz.chat;

import javax.annotation.Nonnull;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import org.apache.commons.lang.Validate;

import cz.chat.actions.AbstractChatAction;
import cz.chat.actions.AllChannelsAction;

public class ChatMenuCreator
{
    private IChatClient client;
    private JTextArea jChatArea;

    public ChatMenuCreator(@Nonnull
    IChatClient client, @Nonnull
    JTextArea jChatArea)
    {
        Validate.notNull(client);
        Validate.notNull(jChatArea);
        this.client = client;
        this.jChatArea = jChatArea;
    }

    public JMenuBar createMenu()
    {
        JMenuBar ret = new JMenuBar();
        JMenu menu = new JMenu("A Menu");
        ret.add(menu);

        JMenuItem menuItem = createMenuItem(new AllChannelsAction(client, jChatArea));
        menu.add(menuItem);
        menuItem = createMenuItem(new AbstractChatAction("all connected users + last message time",
                client,
                jChatArea));
        menu.add(menuItem);
        menuItem = createMenuItem(new AbstractChatAction("last 10 messages", client, jChatArea));
        menu.add(menuItem);
        menuItem = createMenuItem(new AbstractChatAction("users by time to disconnect",
                client,
                jChatArea));
        menu.add(menuItem);

        menuItem = createMenuItem(new AbstractChatAction("users sorted by number of messages",
                client,
                jChatArea));
        menu.add(menuItem);

        menu.addSeparator();
        JMenuItem quitItem = createMenuItem(new AbstractChatAction("Quit", client, jChatArea));
        menu.add(quitItem);
        return ret;
    }

    // TODO some items should be enabled only if connection is established!
    public JMenuItem createMenuItem(AbstractChatAction action)
    {
        JMenuItem menuItem = new JMenuItem(action);
        return menuItem;
    }

}
