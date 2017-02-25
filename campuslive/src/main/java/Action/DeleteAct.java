package Action;

import Dao.ActivityDao;
import Dao.MessageDao;
import Dao.UserDao;
import Entity.Activity;
import Entity.Message;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/21.
 */
public class DeleteAct extends ActionSupport {
    private int actId;
    private String username;

    private ActivityDao activityDao;
    private UserDao userDao;
    private MessageDao messageDao;

    public String execute(){
        Activity activity = activityDao.getActivityById(actId);
        List<User> members = activityDao.getActivityMembers(actId);
        User host = userDao.getUserById(activity.getUserId());

        for (User member : members){
            sendDeleteMessage(activity, member);
        }
        sendDeleteMessage(activity, host);

        activityDao.deleteAct(activity);

        return SUCCESS;
    }

    public void sendDeleteMessage(Activity activity, User user){
        Message message = new Message();

        message.setSenderName(username);
        message.setReceiverName(user.getUsername());

        String content = "非常抱歉，你所报名参与的活动" + activity.getActName() + "已经被我删除。";
        message.setMessageContent(content);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        message.setSendTime(calendar);

        messageDao.saveMessage(message);

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }
}
