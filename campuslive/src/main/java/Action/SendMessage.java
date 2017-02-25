package Action;

import Dao.MessageDao;
import Entity.Activity;
import Entity.Message;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 昱凡 on 2016/7/21.
 */
public class SendMessage extends ActionSupport {
    private String senderName;
    private String receiverName;
    private String messageWord;

    private MessageDao messageDao;

    public String execute(){
        Message message = new Message();

        message.setSenderName(senderName);
        message.setReceiverName(receiverName);
        message.setMessageContent(messageWord);
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        message.setSendTime(calendar);

        messageDao.saveMessage(message);

        return SUCCESS;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public String getMessageWord() {
        return messageWord;
    }

    public void setMessageWord(String messageWord) {
        this.messageWord = messageWord;
    }
}
