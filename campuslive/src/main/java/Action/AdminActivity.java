package Action;

import Dao.ActivityDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 昱凡 on 2016/7/22.
 */
public class AdminActivity extends ActionSupport {
    private int actId;
    private ActivityDao activityDao;

    public String passActivity(){
        activityDao.passActivity(actId);
        return SUCCESS;
    }
    public String denyActivity(){
        activityDao.denyActivity(actId);
        return SUCCESS;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
}
