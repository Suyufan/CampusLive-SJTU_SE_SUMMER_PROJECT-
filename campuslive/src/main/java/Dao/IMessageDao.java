package Dao;

import Entity.Message;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/20.
 */
public interface IMessageDao {
    public void saveMessage(Message message);
    public List<Message> getUnreadMessage(String username);
    public List<Message> getReadMessage(String username);
    public List<Message> getSendMessage(String username);

    public void setRead(int messageId);
    public Long getReadMessageNumber(String username);
    public Long getUnreadMessageNumber(String username);
    public Long getSendMessageNumber(String username);
}
