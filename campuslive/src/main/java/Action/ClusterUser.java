package Action;

import Dao.ActivityDao;
import Entity.Actmem;
import Recommand.Kmeans;
import Recommand.Point;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by 昱凡 on 2016/9/11.
 */
public class ClusterUser extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private Kmeans kmeans;
    private ActivityDao activityDao;

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public Kmeans getKmeans() {
        return kmeans;
    }

    public void setKmeans(Kmeans kmeans) {
        this.kmeans = kmeans;
    }

    public String execute() throws IOException{
        List<Actmem> clusterMember = activityDao.getActMem();

        kmeans.readF1();
        kmeans.Kluster();
        kmeans.CalCentroid();
        kmeans.RecursionKluster();

        List<Point> finalP = kmeans.getFinalP();

        request = ServletActionContext.getRequest();
        session = request.getSession();

        session.removeAttribute("clusterMember");
        session.setAttribute("clusterMember", clusterMember);
        session.removeAttribute("finalP");
        session.setAttribute("finalP", finalP);

        return SUCCESS;
    }
}
