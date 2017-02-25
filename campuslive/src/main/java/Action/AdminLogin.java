package Action;

import Dao.UserDao;
import Entity.Admin;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 昱凡 on 2016/7/21.
 */
public class AdminLogin extends ActionSupport {
    private String adminName;
    private String password;

    private HttpServletRequest request;
    private HttpSession session;
    private UserDao userDao;
    private String message;

    public String execute(){
        Admin admin = userDao.getAdminByName(adminName);
        request = ServletActionContext.getRequest();
        session = request.getSession();

        if (password.equals(admin.getPassword())){
            session.removeAttribute("admin");
            session.setAttribute("admin", admin);

            return SUCCESS;
        }
        else {
            setMessage("用户名或密码错误！");
            return "error";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
