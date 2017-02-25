package Action;

import Dao.TeamDao;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/16.
 */
public class getTeamMem extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private TeamDao teamDao;

    private int teamId;

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<User> members = teamDao.getTeamMem(teamId);
        List<User> otherUsers = teamDao.getAllUser(teamId);



        session.removeAttribute("members");
        session.setAttribute("members", members);
        session.removeAttribute("otherUsers");
        session.setAttribute("otherUsers", otherUsers);
        session.removeAttribute("teamId");
        session.setAttribute("teamId", teamId);

        return SUCCESS;
    }
}
