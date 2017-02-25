package Action;

import Dao.FrontpageDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by 昱凡 on 2016/9/11.
 */
public class DeleteFrontPage extends ActionSupport {
    private FrontpageDao frontpageDao;

    private int actId;

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public FrontpageDao getFrontpageDao() {
        return frontpageDao;
    }

    public void setFrontpageDao(FrontpageDao frontpageDao) {
        this.frontpageDao = frontpageDao;
    }

    public String execute(){
        System.out.println(actId);

        frontpageDao.deleteFrontPage(actId);

        return SUCCESS;
    }
}
