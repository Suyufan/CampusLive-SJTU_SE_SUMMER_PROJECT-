package Action;

import Dao.UserDao;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by 昱凡 on 2016/7/4.
 */
public class Register extends ActionSupport {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private String message;
    private HttpServletRequest request;
    private HttpSession session;

    private int userId;
    private String username;
    private String password;
    private String email;
    private String school;
    private String telephone;

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute(){
        request = ServletActionContext.getRequest();
        session = request.getSession();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setSchool(school);
        user.setTelephone(telephone);


        if ( userDao.getUserByName(username) != null){
            setMessage("用户名已存在！");
            return "error";
        }
        else {
            userDao.saveUser(user);
            session.setAttribute("user", user);

            return SUCCESS;
        }
    }

}
