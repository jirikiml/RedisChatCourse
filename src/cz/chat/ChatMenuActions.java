package cz.chat;

public enum ChatMenuActions
{
    ALL_CONNECTED_USERS("all connected users + last message time"), USERS_BY_ACTIVITY(
            "users sorted by number of messages"), ALL_CHANNELS("all existing channels"), LAST_TEN_MESSAGES(
            "last 10 messages"), USER_BY_DISCONNECT_TIME("users by time to disconnect"), QUIT("Quit");
    private String text;

    private ChatMenuActions(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
