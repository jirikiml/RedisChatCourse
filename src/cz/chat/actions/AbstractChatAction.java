package cz.chat.actions;

import java.awt.event.ActionEvent;

import javax.annotation.Nonnull;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;

import org.apache.commons.lang.Validate;

import cz.chat.IChatClient;

public class AbstractChatAction
        extends AbstractAction
{
    private static final long serialVersionUID = -3703057994603518207L;
    protected JTextArea jChatArea;
    protected IChatClient client;
    private String name;

    public AbstractChatAction(@Nonnull
    String name, @Nonnull
    IChatClient client, @Nonnull
    JTextArea jChatArea)
    {
        super(name);
        Validate.notNull(name);
        Validate.notNull(client);
        Validate.notNull(jChatArea);
        this.client = client;
        this.jChatArea = jChatArea;
        this.name = name;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        jChatArea.append("action: " + name + "\n");

    }

}
