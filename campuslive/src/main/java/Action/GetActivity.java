package Action;

import Dao.ActivityDao;
import Dao.UserDao;
import Entity.Activity;
import Entity.Actrate;
import Entity.User;
import Entity.Usercomment;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/8.
 */
public class GetActivity extends ActionSupport{
    private int actId;

    private HttpServletRequest request;
    private HttpSession session;
    private ActivityDao activityDao;
    private UserDao userDao;

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

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getAllActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> all = activityDao.getAllActivity();

        session.removeAttribute("all");
        session.setAttribute("all", all);
        return SUCCESS;
    }

    public String getActivity(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        Activity activity = activityDao.getActivityById(actId);
        session.removeAttribute("act");
        session.setAttribute("act", activity);

        return SUCCESS;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        System.out.println("In GetActivity Action ......");

        Activity activity = activityDao.getActivityById(actId);
        List<Integer>member = activityDao.getMemberByActId(actId);

       /* if (activity != null){
            System.out.println("Get Activity Successfully !");
        }
        System.out.println(activity.getActName());*/

        int userId = activity.getUserId();
        User host = userDao.getUserById(userId);
        List<String> tags = activityDao.getTagByAct(actId);
        List<Usercomment> comments = activityDao.getCommentByAct(actId);
        List<User> allUser = userDao.getAllUser();
        List<User> members = activityDao.getActivityMembers(actId);

        User user = (User) session.getAttribute("user");
        if (user != null){
            Actrate actrate = new Actrate();
            Date date = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            actrate.setActId(actId);
            actrate.setUserId(user.getUserId());
            actrate.setScore(3);
            actrate.setScoretime(calendar);

            if (activityDao.ifExistedRate(actId, user.getUserId(), 3) == 0){
                activityDao.scoreAct(actrate);
            }
            else if (activityDao.ifExistedRate(actId, user.getUserId(), 3) == 2){
                activityDao.saveRate(actrate);
            }
        }

        session.removeAttribute("tags");
        session.setAttribute("tags", tags);
        session.removeAttribute("host");
        session.setAttribute("host", host);
        session.removeAttribute("member");
        session.setAttribute("member", member);
        session.removeAttribute("members");
        session.setAttribute("members", members);
        session.removeAttribute("activity");
        session.setAttribute("activity", activity);
        session.removeAttribute("comments");
        session.setAttribute("comments", comments);
        session.removeAttribute("allUser");
        session.setAttribute("allUser", allUser);

        return SUCCESS;
    }

}
