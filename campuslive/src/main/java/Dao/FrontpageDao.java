package Dao;

import Entity.Activity;
import Entity.Frontpage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 昱凡 on 2016/9/9.
 */
public class FrontpageDao implements IFrontpageDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Activity> getFrontpage() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actId in (select f.frontId from Frontpage as f)";
        Query query = session.createQuery(hql);

        List<Activity> all = query.list();

        transaction.commit();
        session.close();

        return all;
    }

    public void deleteFrontPage(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "delete from Frontpage as f where f.frontId = ?";
        Query query = session.createQuery(hql).setParameter(0, actId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public void saveFrontPage(Frontpage frontpage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(frontpage);

        transaction.commit();
        session.close();
    }

    public List<Activity> getNoneFrontPage() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actId not in (select f.frontId from Frontpage as f)";
        Query query = session.createQuery(hql);

        List<Activity> all = query.list();

        transaction.commit();
        session.close();

        return all;
    }
}
