package Action;

import Dao.TeamDao;
import Dao.UserDao;
import Entity.Team;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class UserTeam extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;
    private TeamDao teamDao;
    private UserDao userDao;
    private int userId;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedTeam(){
        List<Team> createdTeams = teamDao.getCreatedTeam(userId);

        request = ServletActionContext.getRequest();
        session = request.getSession();

        session.removeAttribute("createdTeams");
        session.setAttribute("createdTeams", createdTeams);

        return SUCCESS;
    }

    private int teamId;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeam(){
        Team team = teamDao.getTeamById(teamId);

        request = ServletActionContext.getRequest();
        session = request.getSession();

        session.removeAttribute("createdTeam");
        session.setAttribute("createdTeam", team);

        return SUCCESS;
    }
    public String addMem(){
        System.out.println(teamId);

        User user = userDao.getUserByName(username);
        teamDao.joinTeam(user.getUserId(), teamId);

        return SUCCESS;
    }
    public String removeMem(){
        teamDao.leaveTeam(userId, teamId);

        return SUCCESS;
    }
}
