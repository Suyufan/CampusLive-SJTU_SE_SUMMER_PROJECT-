package Dao;

import Entity.*;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/8.
 */
public interface IActivityDao {
    public void saveActivity(Activity activity);
    public void saveTag(int actId, int tagId);
    public Activity getActivityById(int actId);
    public Activity getActivityById(Long actId);

    public List<Integer> getMemberByActId(int actId);
    public void deleteFromActmem(int userId, int actId);
    public void addToActmem(int userId, int actId);
    public List<String> getTagByAct(int actId);
    public List<Activity> getNotStartedActivity(int userId);
    public List<Activity> getFinishedActivity(int userId);
    public List<Activity> getActivityByUser(int userId);
    public List<Activity> getActivityByHost(int hostId);

    public List<Team> getAllGroups();
    public List<Team> getGroupsAsMember(int userId);
    public List<Tag> getAllTags();

    public List<Activity> getLatestAct();
    public List<Activity> getHottestAct();
    public List<Activity> getAllActivity();

    public List<User> getActivityMembers(int actId);
    public void updateActivity(Activity activity);

    public List<Usercomment> getCommentByAct(int actId);

    public List<Activity> searchActivity(String searchWord);

    public void scoreAct(Actrate actrate);

    public void saveRate(Actrate actrate);

    public int ifExistedRate(int actId, int userId, int score);

    public void deleteAct(Activity activity);

    public List<Activity> getUncheckedAct();

    public List<Activity> getPassedAct();
    public List<Activity> getDeniedAct();

    public void passActivity(int actId);

    public void denyActivity(int actId);

    public List<Actmem> getActMem();
}
