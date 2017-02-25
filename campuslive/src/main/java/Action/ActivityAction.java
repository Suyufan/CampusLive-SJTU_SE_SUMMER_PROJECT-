package Action;

import Dao.ActivityDao;
import Entity.Actrate;
import com.opensymphony.xwork2.ActionSupport;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 昱凡 on 2016/7/10.
 */
public class ActivityAction extends ActionSupport {
    private HttpSession session;
    private HttpServletRequest request;

    private ActivityDao activityDao;
    private int userId;
    private int actId;

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

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String joinActivity(){
        activityDao.addToActmem(userId, actId);

        Actrate actrate = new Actrate();
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        actrate.setActId(actId);
        actrate.setUserId(userId);
        actrate.setScore(4);
        actrate.setScoretime(calendar);

        if (activityDao.ifExistedRate(actId, userId, 4) == 0){
            activityDao.scoreAct(actrate);
        }
        else if (activityDao.ifExistedRate(actId, userId, 4) == 2){
            activityDao.saveRate(actrate);
        }

        return SUCCESS;
    }

    public String leaveActivity(){
        activityDao.deleteFromActmem(userId,actId);
        return SUCCESS;
    }
}
