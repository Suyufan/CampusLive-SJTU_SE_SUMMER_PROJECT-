package Action;

import Dao.TeamDao;
import Entity.Team;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class UpdateTeam extends ActionSupport {
    private TeamDao teamDao;

    private int teamId;
    private String teamName;
    private String teamDesc;

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

    public String execute(){
        Team team = new Team();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        team.setTeamDesc(teamDesc);

        teamDao.updateTeam(team);

        return SUCCESS;
    }
}
