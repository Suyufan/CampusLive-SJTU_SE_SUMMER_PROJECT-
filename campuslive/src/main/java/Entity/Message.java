package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/7/20.
 */
@Entity
public class Message {
    private int messageId;
    private String senderName;
    private String receiverName;
    private String messageContent;
    private int messageStat;
    private Calendar sendTime;

    @Id
    @Column(name = "messageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "senderName")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Basic
    @Column(name = "receiverName")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Basic
    @Column(name = "messageContent")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "messageStat")
    public int getMessageStat() {
        return messageStat;
    }

    public void setMessageStat(int messageStat) {
        this.messageStat = messageStat;
    }

    @Basic
    @Column(name = "sendTime")
    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (messageStat != message.messageStat) return false;
        if (senderName != null ? !senderName.equals(message.senderName) : message.senderName != null) return false;
        if (receiverName != null ? !receiverName.equals(message.receiverName) : message.receiverName != null)
            return false;
        if (messageContent != null ? !messageContent.equals(message.messageContent) : message.messageContent != null)
            return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + messageStat;
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        return result;
    }
}
