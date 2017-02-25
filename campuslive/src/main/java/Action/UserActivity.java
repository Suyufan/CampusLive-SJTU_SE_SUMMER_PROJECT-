package Action;

import Dao.ActivityDao;
import Dao.UserDao;
import Entity.Activity;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/11.
 */
public class UserActivity extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private ActivityDao activityDao;
    private UserDao userDao;

    private int userId;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNotStartedActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> notStartedActivity = activityDao.getNotStartedActivity(userId);

        session.removeAttribute("notStartedActivity");
        session.setAttribute("notStartedActivity", notStartedActivity);

        return SUCCESS;
    }
    public String getFinishedActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> finishedActivity = activityDao.getFinishedActivity(userId);

        session.removeAttribute("finishedActivity");
        session.setAttribute("finishedActivity", finishedActivity);
        return SUCCESS;
    }
    public String getAllActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> allActivity = activityDao.getActivityByUser(userId);

        session.removeAttribute("allActivity");
        session.setAttribute("allActivity", allActivity);
        return SUCCESS;
    }
    public String getCreatedActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> createdActivity = activityDao.getActivityByHost(userId);

        session.removeAttribute("createdActivity");
        session.setAttribute("createdActivity", createdActivity);
        return SUCCESS;
    }
    public String getAllUser(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<User> allUser = userDao.getAllUser();

        session.removeAttribute("users");
        session.setAttribute("users", allUser);

        return SUCCESS;
    }

    public String getUncheckedAct(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> uncheckedAct = activityDao.getUncheckedAct();

        session.removeAttribute("uncheckedAct");
        session.setAttribute("uncheckedAct", uncheckedAct);

        return SUCCESS;
    }

    public String getPassedAct(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> passedAct = activityDao.getPassedAct();

        session.removeAttribute("passedAct");
        session.setAttribute("passedAct", passedAct);

        return SUCCESS;
    }

    public String getDeniedAct(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> deniedAct = activityDao.getDeniedAct();

        session.removeAttribute("deniedAct");
        session.setAttribute("deniedAct", deniedAct);

        return SUCCESS;
    }
}
