package Action;

import Dao.TeamDao;
import Entity.Team;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class createTeam extends ActionSupport {
    private TeamDao teamDao;

    private String teamName;
    private String teamDesc;
    private int userId;

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String execute(){
        Team team = new Team();
        team.setTeamName(teamName);
        team.setUserId(userId);
        team.setTeamDesc(teamDesc);
        team.setNumOfMembers(1);

        teamDao.saveTeam(team);

        return SUCCESS;
    }
}
