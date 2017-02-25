package Service;

import Entity.User;

/**
 * Created by 昱凡 on 2016/7/6.
 */
public interface IUserService {
    public User checkUser(String username, String password);
}
