package Action;

import Dao.ActivityDao;
import Entity.Tag;
import Entity.Team;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/13.
 */
public class GetTagAndGroup extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private ActivityDao activityDao;

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String execute(){

        request = ServletActionContext.getRequest();
        session = request.getSession();

       User user = (User) session.getAttribute("user");


        List<Tag> tags = activityDao.getAllTags();
        List<Team> teams = activityDao.getGroupsAsMember(user.getUserId());

        session.removeAttribute("tags");
        session.removeAttribute("teams");
        session.setAttribute("tags", tags);
        session.setAttribute("teams", teams);

        return SUCCESS;
    }
}
