package Action;

import Dao.MessageDao;
import Entity.Activity;
import Entity.Message;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/20.
 */
public class GetMessage extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;
    private MessageDao messageDao;

    private String username;
    private int messageId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnreadMessage(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Message> unreadMessages = messageDao.getUnreadMessage(username);

        session.removeAttribute("unreadMessages");
        session.setAttribute("unreadMessages", unreadMessages);

        return SUCCESS;
    }

    public String getReadMessage(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Message> readMessages = messageDao.getReadMessage(username);

        session.removeAttribute("readMessages");
        session.setAttribute("readMessages", readMessages);

        return SUCCESS;
    }

    public String getSendMessage(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Message> sendMessages = messageDao.getSendMessage(username);

        session.removeAttribute("sendMessages");
        session.setAttribute("sendMessages", sendMessages);

        return SUCCESS;
    }

    public String setRead(){
        messageDao.setRead(messageId);

        return SUCCESS;
    }
}
