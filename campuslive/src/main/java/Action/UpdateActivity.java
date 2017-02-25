package Action;

import Dao.ActivityDao;
import Entity.Activity;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public class UpdateActivity extends ActionSupport {
    private int userId;
    private int actId;
    private String actName;
    private String actLocation;
    private String actDesc;
    private int maxMember;
    private Calendar actStartTime;
    private Calendar actEndTime;
    private Calendar regStartTime;
    private Calendar regEndTime;

    private ActivityDao activityDao;

    public String execute(){
        System.out.println(regStartTime);
        System.out.println(regEndTime);
        System.out.println(actStartTime);
        System.out.println(actStartTime);

        Activity activity = new Activity();
        activity.setActId(actId);
        activity.setActName(actName);
        activity.setActLocation(actLocation);
        activity.setActDesc(actDesc);
        activity.setMaxMember(maxMember);
        activity.setRegStartTime(regStartTime);
        activity.setRegEndTime(regEndTime);
        activity.setActStartTime(actStartTime);
        activity.setActEndTime(actEndTime);

        activityDao.updateActivity(activity);

        return SUCCESS;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
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

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
}
