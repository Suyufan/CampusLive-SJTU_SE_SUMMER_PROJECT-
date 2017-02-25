package Action;

import Dao.FrontpageDao;
import Entity.Frontpage;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 昱凡 on 2016/9/11.
 */
public class AddFrontPage extends ActionSupport {
    private int actId;
    private FrontpageDao frontpageDao;

    public FrontpageDao getFrontpageDao() {
        return frontpageDao;
    }

    public void setFrontpageDao(FrontpageDao frontpageDao) {
        this.frontpageDao = frontpageDao;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String execute(){
        Frontpage frontpage = new Frontpage();

        frontpage.setFrontId(actId);

        frontpageDao.saveFrontPage(frontpage);

        return SUCCESS;
    }
}
