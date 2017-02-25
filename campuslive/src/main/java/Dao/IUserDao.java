package Dao;

import Entity.Admin;
import Entity.User;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/5.
 */
public interface IUserDao {
    public void saveUser(User user);
    public User getUserByName(String username);
    public User getUserById(int userId);
    public void updateUser(User user);

    public List<User> getAllUser();

    public Admin getAdminByName(String adminName);
}
