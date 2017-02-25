package Action;

import Dao.ActivityDao;
import Dao.TeamDao;
import Entity.Activity;
import Entity.Actrate;
import Entity.Team;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/20.
 */
public class SearchActivity extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private String searchWord;
    private ActivityDao activityDao;
    private TeamDao teamDao;

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> searchResult = activityDao.searchActivity(searchWord);
        List<Team> teamResult = teamDao.searchTeam(searchWord);

        User user = (User) session.getAttribute("user");
        if (user != null){
            for (Activity activity : searchResult){
                Actrate actrate = new Actrate();
                Date date = new Date();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                actrate.setActId(activity.getActId());
                actrate.setUserId(user.getUserId());
                actrate.setScore(2);
                actrate.setScoretime(calendar);

                if (activityDao.ifExistedRate(activity.getActId(), user.getUserId(), 2) == 0){
                    activityDao.scoreAct(actrate);
                }
                else if (activityDao.ifExistedRate(activity.getActId(), user.getUserId(), 2) == 2){
                    activityDao.saveRate(actrate);
                }
            }
        }

        session.removeAttribute("teamResult");
        session.setAttribute("teamResult", teamResult);
        session.removeAttribute("searchResult");
        session.setAttribute("searchResult", searchResult);

        return SUCCESS;
    }
}
