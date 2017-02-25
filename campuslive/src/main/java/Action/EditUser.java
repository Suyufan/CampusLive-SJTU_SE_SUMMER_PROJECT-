package Action;

import Dao.UserDao;
import Entity.User;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public class EditUser extends ActionSupport {
    private HttpServletRequest request;
    private HttpSession session;

    private int userId;
    private String email;
    private String school;
    private String telephone;
    private UserDao userDao;

    public String execute(){
        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        user.setSchool(school);
        user.setTelephone(telephone);

        userDao.updateUser(user);

        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
