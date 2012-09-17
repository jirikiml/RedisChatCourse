package cz.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

public class SwingChatGUI
        extends JFrame
{
    private static final String CHANNEL = "QQQ";

    private static final long serialVersionUID = 6563086133065802861L;

    private String login;
    private javax.swing.JButton jSayButton;
    private javax.swing.JButton jConnectButton;
    private javax.swing.JButton jDisconnectButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jChatArea;
    private javax.swing.JTextField jInputField;

    private IChatClient client = null;

    public SwingChatGUI(IChatClient client)
    {
        this.client = client;
    }

    private void addButtonListeners(final ChatPubSubListener pubSubListener)
    {
        ActionListener sayButtonActionListener = new SayButtonActionListener(jInputField,
                this.client,
                CHANNEL);
        jSayButton.addActionListener(sayButtonActionListener);
        jConnectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    login = JOptionPane.showInputDialog("Login: ");
                    if (!StringUtils.isEmpty(login))
                    {
                        pubSubListener.setLogin(login);
                        client.setLogin(login);
                        SwingChatGUI.this.client.connect("localhost", 6379);
                        SwingChatGUI.this.client.selectDB(12);
                        SwingChatGUI.this.client.subscribe(CHANNEL, pubSubListener);
                        handleVisibility(true);
                    }
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(SwingChatGUI.this,
                            "Error",
                            exception.toString(),
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        jDisconnectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    SwingChatGUI.this.client.unSubscribe(CHANNEL);
                    SwingChatGUI.this.client.disconnect();
                    handleVisibility(false);
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(SwingChatGUI.this,
                            "Error",
                            exception.toString(),
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // TODO move to view class
    private void initComponents()
    {
        jScrollPane1 = new javax.swing.JScrollPane();
        jChatArea = new javax.swing.JTextArea();
        jSayButton = new javax.swing.JButton();
        jConnectButton = new javax.swing.JButton();
        jDisconnectButton = new javax.swing.JButton();
        jDisconnectButton.setEnabled(false);
        jInputField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat 0.5");
        setName("main");
        jChatArea.setColumns(20);
        jChatArea.setEditable(false);
        jChatArea.setRows(5);
        jScrollPane1.setViewportView(jChatArea);

        jSayButton.setText("Say");
        jSayButton.setEnabled(false);

        jConnectButton.setText("Connect");
        jDisconnectButton.setText("Disconnect");

        jInputField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1,
                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                394,
                                                Short.MAX_VALUE)
                                        .addGroup(layout
                                                .createSequentialGroup()
                                                .addComponent(jInputField,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        236,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSayButton,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        73,
                                                        Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jConnectButton)
                                                .addComponent(jDisconnectButton)))
                                .addContainerGap()));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        249,
                                        Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jConnectButton)
                                        .addComponent(jDisconnectButton)
                                        .addComponent(jSayButton)
                                        .addComponent(jInputField,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        ChatMenuListener menuListener = new ChatMenuListener();
        ChatMenuCreator menuCreator = new ChatMenuCreator(menuListener);
        JMenuBar menuBar = menuCreator.createMenu();
        setJMenuBar(menuBar);
        pack();
    }

    private void handleVisibility(boolean connected)
    {
        jSayButton.setEnabled(connected);
        jInputField.setEnabled(connected);
        jConnectButton.setEnabled(!connected);
        jDisconnectButton.setEnabled(connected);
    }

    public static void main(String[] args)
    {
        // IChatClient client = new MockClient();
        IChatClient client = new RedisClient();
        // TODO invoke later!
        SwingChatGUI gui = new SwingChatGUI(client);
        gui.initComponents();
        ChatPubSubListener pubSubListener = new ChatPubSubListener(gui.jChatArea, gui.client);
        gui.addButtonListeners(pubSubListener);
        gui.setVisible(true);
    }

}