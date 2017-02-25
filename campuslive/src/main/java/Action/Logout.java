package Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 昱凡 on 2016/7/16.
 */
public class Logout extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        session.invalidate();

        return SUCCESS;
    }
}
