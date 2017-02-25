package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/7/8.
 */
@Entity
public class Activity {
    private int actId;
    private String actName;
    private String actLocation;
    private String actDesc;
    private int userId;
    private Integer groupId;
    private int actStat;
    private int currentMember;
    private int maxMember;
    private Calendar actStartTime;
    private Calendar actEndTime;
    private Calendar regStartTime;
    private Calendar regEndTime;
    private String postPath;

    @Id
    @Column(name = "actId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "actName")
    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    @Basic
    @Column(name = "actLocation")
    public String getActLocation() {
        return actLocation;
    }

    public void setActLocation(String actLocation) {
        this.actLocation = actLocation;
    }

    @Basic
    @Column(name = "actDesc")
    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "groupId")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "actStat")
    public int getActStat() {
        return actStat;
    }

    public void setActStat(int actStat) {
        this.actStat = actStat;
    }

    @Basic
    @Column(name = "currentMember")
    public int getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(int currentMember) {
        this.currentMember = currentMember;
    }

    @Basic
    @Column(name = "maxMember")
    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    @Basic
    @Column(name = "actStartTime")
    public Calendar getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Calendar actStartTime) {
        this.actStartTime = actStartTime;
    }

    @Basic
    @Column(name = "actEndTime")
    public Calendar getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(Calendar actEndTime) {
        this.actEndTime = actEndTime;
    }

    @Basic
    @Column(name = "regStartTime")
    public Calendar getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Calendar regStartTime) {
        this.regStartTime = regStartTime;
    }

    @Basic
    @Column(name = "regEndTime")
    public Calendar getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Calendar regEndTime) {
        this.regEndTime = regEndTime;
    }

    @Basic
    @Column(name = "postPath")
    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (actId != activity.actId) return false;
        if (userId != activity.userId) return false;
        if (actStat != activity.actStat) return false;
        if (currentMember != activity.currentMember) return false;
        if (maxMember != activity.maxMember) return false;
        if (actName != null ? !actName.equals(activity.actName) : activity.actName != null) return false;
        if (actLocation != null ? !actLocation.equals(activity.actLocation) : activity.actLocation != null)
            return false;
        if (actDesc != null ? !actDesc.equals(activity.actDesc) : activity.actDesc != null) return false;
        if (groupId != null ? !groupId.equals(activity.groupId) : activity.groupId != null) return false;
        if (actStartTime != null ? !actStartTime.equals(activity.actStartTime) : activity.actStartTime != null)
            return false;
        if (actEndTime != null ? !actEndTime.equals(activity.actEndTime) : activity.actEndTime != null) return false;
        if (regStartTime != null ? !regStartTime.equals(activity.regStartTime) : activity.regStartTime != null)
            return false;
        if (regEndTime != null ? !regEndTime.equals(activity.regEndTime) : activity.regEndTime != null) return false;
        if (postPath != null ? !postPath.equals(activity.postPath) : activity.postPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actId;
        result = 31 * result + (actName != null ? actName.hashCode() : 0);
        result = 31 * result + (actLocation != null ? actLocation.hashCode() : 0);
        result = 31 * result + (actDesc != null ? actDesc.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + actStat;
        result = 31 * result + currentMember;
        result = 31 * result + maxMember;
        result = 31 * result + (actStartTime != null ? actStartTime.hashCode() : 0);
        result = 31 * result + (actEndTime != null ? actEndTime.hashCode() : 0);
        result = 31 * result + (regStartTime != null ? regStartTime.hashCode() : 0);
        result = 31 * result + (regEndTime != null ? regEndTime.hashCode() : 0);
        result = 31 * result + (postPath != null ? postPath.hashCode() : 0);
        return result;
    }
}
