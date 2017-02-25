package Dao;

import Entity.Activity;
import Entity.Team;
import Entity.User;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public interface ITeamDao {
    public void saveTeam(Team team);
    public void joinTeam(int userId, int teamId);
    public void leaveTeam(int userId, int teamId);
    public List<Team> getCreatedTeam(int userId);
    public Team getTeamById(int teamId);
    public Team getTeamByName(String teamName);
    public void updateTeam(Team team);

    public List<User> getTeamMem(int teamId);
    public List<User> getAllUser(int teamId);

    public List<Team> searchTeam(String searchWord);
    public List<Activity> getTeamAct(int teamId);
}
