package Action;

import Dao.ActivityDao;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public class GetActMem extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private ActivityDao activityDao;
    private int actId;

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<User> members = activityDao.getActivityMembers(actId);

        session.removeAttribute("actMem");
        session.setAttribute("actMem", members);

        return SUCCESS;
    }
}
