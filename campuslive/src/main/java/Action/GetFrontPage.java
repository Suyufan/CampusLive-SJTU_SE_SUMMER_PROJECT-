package Action;

import Dao.ActivityDao;
import Dao.FrontpageDao;
import Entity.Activity;
import Recommand.RecommandEngine;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 昱凡 on 2016/9/11.
 */
public class GetFrontPage extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private FrontpageDao frontpageDao;

    public FrontpageDao getFrontpageDao() {
        return frontpageDao;
    }

    public void setFrontpageDao(FrontpageDao frontpageDao) {
        this.frontpageDao = frontpageDao;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        List<Activity> frontPage = frontpageDao.getFrontpage();
        List<Activity> noneFrontPage = frontpageDao.getNoneFrontPage();

        session.removeAttribute("adminFrontPage");
        session.setAttribute("adminFrontPage", frontPage);
        session.removeAttribute("noneFrontPage");
        session.setAttribute("noneFrontPage", noneFrontPage);

        return SUCCESS;
    }
}
