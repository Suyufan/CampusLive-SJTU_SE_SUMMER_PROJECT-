package Action;

import Dao.ActivityDao;
import Dao.FrontpageDao;
import Dao.MessageDao;
import Entity.Activity;
import Entity.User;
import Recommand.RecommandEngine;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class GetIndex extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private ActivityDao activityDao;
    private MessageDao messageDao;
    private FrontpageDao frontpageDao;
    private RecommandEngine recommandEngine;

    public RecommandEngine getRecommandEngine() {
        return recommandEngine;
    }

    public void setRecommandEngine(RecommandEngine recommandEngine) {
        this.recommandEngine = recommandEngine;
    }

    public FrontpageDao getFrontpageDao() {
        return frontpageDao;
    }

    public void setFrontpageDao(FrontpageDao frontpageDao) {
        this.frontpageDao = frontpageDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public String execute(){
        List<Activity> latest = activityDao.getLatestAct();
        List<Activity> hottest = activityDao.getHottestAct();
        List<Activity> frontpage = frontpageDao.getFrontpage();

        request = ServletActionContext.getRequest();
        session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null){
            Long readMessageNumber = messageDao.getReadMessageNumber(user.getUsername());
            Long unreadMessageNumber = messageDao.getUnreadMessageNumber(user.getUsername());
            Long sendMessageNumber = messageDao.getSendMessageNumber(user.getUsername());

            session.removeAttribute("readMessageNumber");
            session.setAttribute("readMessageNumber", readMessageNumber);
            session.removeAttribute("unreadMessageNumber");
            session.setAttribute("unreadMessageNumber", unreadMessageNumber);
            session.removeAttribute("sendMessageNumber");
            session.setAttribute("sendMessageNumber", sendMessageNumber);

            List<Long> recommandId = recommandEngine.baseUserCF(user.getUserId());

            List<Activity> recommandAct = new ArrayList<Activity>();

            for(long id : recommandId){
                recommandAct.add(activityDao.getActivityById(id));
            }

            recommandAct.add(activityDao.getActivityById(5));

            session.removeAttribute("recommandAct");
            session.setAttribute("recommandAct", recommandAct);

        }


        session.removeAttribute("frontpage");
        session.setAttribute("frontpage", frontpage);
        session.removeAttribute("latest");
        session.setAttribute("latest", latest);
        session.removeAttribute("hottest");
        session.setAttribute("hottest", hottest);

        return SUCCESS;
    }
}
