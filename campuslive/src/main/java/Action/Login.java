package Action;

import Entity.User;
import Service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 昱凡 on 2016/7/6.
 */
public class Login extends ActionSupport {
    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private String message;

    private String username;
    private String password;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        User user = userService.checkUser(username, password);

        if (user != null){
            session.setAttribute("user", user);

            return SUCCESS;
        }
        else {
            setMessage("无效的用户名或者密码 ！");
            return "error";
        }
    }
}
