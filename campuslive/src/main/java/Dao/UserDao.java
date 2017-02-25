package Dao;

import Entity.Admin;
import Entity.Tag;
import Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/5.
 */
public class UserDao implements IUserDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUser(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User getUserByName(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User u where u.username = ?";
        Query query = session.createQuery(hql).setParameter(0, username);

        User user = (User)query.uniqueResult();

        if (user != null){
            System.out.println("Login------Username existed!");
            transaction.commit();
            session.close();
            return user;
        }
        else {
            System.out.println("Login------Username do not exist!!");
            transaction.commit();
            session.close();
            return null;
        }
    }

    public User getUserById(int userId) {
        Session session = sessionFactory.openSession();
        User user =  (User) session.get(User.class, userId);
        session.close();
        return user;
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql="update User as u set u.email=?, u.school=?, u.telephone=? where u.userId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, user.getEmail());
        query.setParameter(1, user.getSchool());
        query.setParameter(2, user.getTelephone());
        query.setParameter(3, user.getUserId());

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User as u";
        Query query = session.createQuery(hql);

        List<User> allUser = query.list();

        transaction.commit();
        session.close();
        return allUser;
    }

    public Admin getAdminByName(String adminName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Admin as a where a.adminName = ?";
        Query query = session.createQuery(hql).setParameter(0, adminName);

        Admin admin = (Admin) query.uniqueResult();

        transaction.commit();
        session.close();
        return admin;
    }
}
