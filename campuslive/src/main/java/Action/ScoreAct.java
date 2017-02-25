package Action;

import Dao.ActivityDao;
import Entity.Actmem;
import Entity.Actrate;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 昱凡 on 2016/7/21.
 */
public class ScoreAct extends ActionSupport {
    private int userId;
    private int actId;
    private int score;

    private ActivityDao activityDao;

    public String execute(){
        Actrate actrate = new Actrate();
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        actrate.setActId(actId);
        actrate.setUserId(userId);
        actrate.setScore(score);
        actrate.setScoretime(calendar);

        activityDao.scoreAct(actrate);

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
}
