package Service;

import Dao.UserDao;
import Entity.User;


/**
 * Created by 昱凡 on 2016/7/6.
 */
public class UserService implements IUserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User checkUser(String username, String password) {
        User user = userDao.getUserByName(username);

        if (user != null){
            if (password.equals(user.getPassword())){
                return user;
            }
            else return null;
        }
        else {
            return null;
        }
    }
}
