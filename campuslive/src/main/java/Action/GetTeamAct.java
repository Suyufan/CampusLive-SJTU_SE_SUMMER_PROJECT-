package Action;

import Dao.TeamDao;
import Entity.Activity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/9/10.
 */
public class GetTeamAct extends ActionSupport {
    private TeamDao teamDao;
    private HttpServletRequest request;
    private HttpSession session;

    private int teamId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public String execute(){
        List<Activity> teamAct = teamDao.getTeamAct(teamId);

        request = ServletActionContext.getRequest();
        session = request.getSession();

        session.removeAttribute("teamAct");
        session.setAttribute("teamAct", teamAct);

        return SUCCESS;
    }
}
