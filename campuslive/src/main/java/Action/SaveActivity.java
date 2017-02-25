package Action;

import Dao.ActivityDao;
import Dao.TeamDao;
import Entity.Activity;
import Entity.Team;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/7/14.
 */
public class SaveActivity extends ActionSupport {
    private String actName;
    private String actLocation;
    private String actDesc;
    private int userId;
    private String groupName;
    private int maxMember;
    private Calendar actStartTime;
    private Calendar actEndTime;
    private Calendar regStartTime;
    private Calendar regEndTime;
    private String postPath;

    private int[] tags;

    private ActivityDao activityDao;
    private TeamDao teamDao;

    public String execute(){
        Activity activity = new Activity();
        activity.setActName(actName);
        activity.setActLocation(actLocation);
        activity.setActDesc(actDesc);
        activity.setUserId(userId);
        // activity.setGroupId(groupId);
        activity.setMaxMember(maxMember);
        activity.setRegStartTime(regStartTime);
        activity.setRegEndTime(regEndTime);
        activity.setActStartTime(actStartTime);
        activity.setActEndTime(actEndTime);
        activity.setPostPath(postPath);
        activity.setActStat(6);

        if (groupName != null){
            Team team = teamDao.getTeamByName(groupName);
            if (team != null){
                activity.setGroupId(team.getTeamId());
            }
        }

        activityDao.saveActivity(activity);

        int actId = activity.getActId();

        for (int t : tags){
            System.out.println(actId + "--" + t);
            activityDao.saveTag(actId, t);
        }


       /* System.out.println(regStartTime);
        System.out.println(regEndTime);
        System.out.println(actStartTime);
        System.out.println(actEndTime);
        System.out.println("----------------------------");
        for (int t : tags){
            System.out.println(t);
        }
        System.out.println("----------------------------");
        System.out.println(groupName);
        System.out.println(userId);
        System.out.println("----------------------------");
        System.out.println(actDesc);
        System.out.println("----------------------------");
        System.out.println(postPath);*/

        /*activityDao.saveActivity(activity);*/

        return SUCCESS;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActLocation() {
        return actLocation;
    }

    public void setActLocation(String actLocation) {
        this.actLocation = actLocation;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public Calendar getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Calendar actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Calendar getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(Calendar actEndTime) {
        this.actEndTime = actEndTime;
    }

    public Calendar getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Calendar regStartTime) {
        this.regStartTime = regStartTime;
    }

    public Calendar getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Calendar regEndTime) {
        this.regEndTime = regEndTime;
    }

    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }
}
