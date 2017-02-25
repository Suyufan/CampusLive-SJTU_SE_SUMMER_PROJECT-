package Dao;

import Entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/8.
 */
public class ActivityDao implements IActivityDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveActivity(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(activity);

        System.out.println(activity.getActId());

        transaction.commit();
        session.close();
    }

    public Activity getActivityById(int actId) {
        Session session = sessionFactory.openSession();

        Activity activity = (Activity) session.get(Activity.class, actId);

        session.close();

        return activity;
    }

    public Activity getActivityById(Long actId) {
        Session session = sessionFactory.openSession();

        int id = actId.intValue();

        Activity activity = (Activity) session.get(Activity.class, id);

        session.close();

        return activity;
    }

    public void addToActmem(int userId, int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Actmem actmem = new Actmem();
        actmem.setUserId(userId);
        actmem.setActId(actId);
        session.save(actmem);

        transaction.commit();
        session.close();
    }

    public void deleteFromActmem(int userId, int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Actmem actmem = new Actmem();
        actmem.setUserId(userId);
        actmem.setActId(actId);

        session.delete(actmem);

        transaction.commit();
        session.close();
    }

    public List<Integer> getMemberByActId(int actId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select a.userId from Actmem a where a.actId = ?";
        Query query = session.createQuery(hql).setParameter(0, actId);

        List<Integer> members = query.list();

        transaction.commit();
        session.close();
        return members;
    }

    public List<String> getTagByAct(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select t.tagName from Tag as t where t.tagId in (select a.tagId from Acttag as a where a.actId = ?) ";
        Query query = session.createQuery(hql).setParameter(0, actId);

        List<String> tags = query.list();

        transaction.commit();
        session.close();

        return tags;
    }

    public List<Activity> getNotStartedActivity(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat <= 3 and a.actId in (select t.actId from Actmem as t where t.userId = ?)";
        Query query = session.createQuery(hql).setParameter(0, userId);

        List<Activity> notStartedActivity = query.list();

        transaction.commit();
        session.close();
        return notStartedActivity;
    }

    public List<Activity> getFinishedActivity(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat = 5 and a.actId in (select t.actId from Actmem as t where t.userId = ?)";
        Query query = session.createQuery(hql).setParameter(0, userId);

        List<Activity> finishedActivity = query.list();

        transaction.commit();
        session.close();
        return finishedActivity;
    }

    public List<Activity> getActivityByUser(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actId in (select t.actId from Actmem as t where t.userId = ?)";
        Query query = session.createQuery(hql).setParameter(0, userId);

        List<Activity> allActivity = query.list();

        transaction.commit();
        session.close();
        return allActivity;
    }

    public List<Activity> getActivityByHost(int hostId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.userId = ?";
        Query query = session.createQuery(hql).setParameter(0, hostId);

        List<Activity> createdActivity = query.list();

        transaction.commit();
        session.close();
        return createdActivity;
    }

    public List<Team> getAllGroups() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Team as g";
        Query query = session.createQuery(hql);

        List<Team> teams = query.list();

        transaction.commit();
        session.close();

        return teams;
    }

    public List<Team> getGroupsAsMember(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Team as t where t.teamId in (select tm.teamId from Teammem as tm where tm.userId = ?)";
        Query query = session.createQuery(hql).setParameter(0, userId);

        List<Team> teams = query.list();

        transaction.commit();
        session.close();

        return teams;
    }

    public List<Tag> getAllTags() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Tag as t";
        Query query = session.createQuery(hql);

        List<Tag> tags = query.list();

        transaction.commit();
        session.close();

        return tags;
    }

    public List<Activity> getLatestAct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat < 6 order by a.actId desc ";
        Query query = session.createQuery(hql);
        query.setFirstResult(1);
        query.setMaxResults(3);
        List<Activity> latest = query.list();

        transaction.commit();
        session.close();
        return latest;
    }

    public List<Activity> getHottestAct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat < 6 order by a.currentMember/a.maxMember desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(1);
        query.setMaxResults(3);
        List<Activity> hottest = query.list();

        transaction.commit();
        session.close();
        return hottest;
    }

    public List<Activity> getAllActivity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat < 6";
        Query query = session.createQuery(hql);

        List<Activity> all = query.list();

        transaction.commit();
        session.close();

        return all;
    }

    public void saveTag(int actId, int tagId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Acttag acttag = new Acttag();
        acttag.setActId(actId);
        acttag.setTagId(tagId);

        session.save(acttag);

        transaction.commit();
        session.close();
    }

    public List<User> getActivityMembers(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User as u where u.userId in (select a.userId from Actmem as a where a.actId = ?)";
        Query query = session.createQuery(hql).setParameter(0, actId);

        List<User> members = query.list();

        transaction.commit();
        session.close();

        return members;
    }

    public void updateActivity(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Activity as a set a.actName = ?, a.actLocation = ?, a.maxMember = ?, a.actDesc = ?, " +
                "a.regStartTime = ?, a.regEndTime = ?, a.actStartTime = ?, a.actEndTime = ? where a.actId = ?";
        Query query = session.createQuery(hql);

        query.setParameter(0, activity.getActName());
        query.setParameter(1, activity.getActLocation());
        query.setParameter(2, activity.getMaxMember());
        query.setParameter(3, activity.getActDesc());
        query.setParameter(4, activity.getRegStartTime());
        query.setParameter(5, activity.getRegEndTime());
        query.setParameter(6, activity.getActStartTime());
        query.setParameter(7, activity.getActEndTime());
        query.setParameter(8, activity.getActId());

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public List<Usercomment> getCommentByAct(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql ="from Usercomment as u where u.actId = ? ";

        Query query = session.createQuery(hql).setParameter(0, actId);
        List<Usercomment> comments = query.list();

        transaction.commit();
        session.close();
        return comments;
    }

    public List<Activity> searchActivity(String searchWord) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actName like ? and a.actStat <> 6 and a.actStat <> 7";
        Query query = session.createQuery(hql).setParameter(0, "%"+searchWord+"%");

        List<Activity> result = query.list();

        transaction.commit();
        session.close();

        return result;
    }

    public void scoreAct(Actrate actrate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(actrate);

        transaction.commit();
        session.close();
    }

    public void saveRate(Actrate actrate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(actrate);

        transaction.commit();
        session.close();
    }

    public int ifExistedRate(int actId, int userId, int score) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Actrate as ar where ar.actId = ? and userId = ? ";
        Query query = session.createQuery(hql).setParameter(0, actId).setParameter(1, userId);

        List list = query.list();

        if (list.size() != 0){
            Actrate actrate = (Actrate) list.get(0);
            int pscore = actrate.getScore();

            if (pscore < score){
                transaction.commit();
                session.close();
                return 0;
            }
            else {
                transaction.commit();
                session.close();
                return 1;
            }
        }
        else {
            transaction.commit();
            session.close();
            return 2;
        }
    }

    public void deleteAct(Activity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(activity);

        transaction.commit();
        session.close();
    }

    public List<Activity> getUncheckedAct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat = 6";
        Query query = session.createQuery(hql);

        List<Activity> uncheckedAct = query.list();

        transaction.commit();
        session.close();
        return uncheckedAct;
    }

    public List<Activity> getPassedAct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat < 6";
        Query query = session.createQuery(hql);

        List<Activity> passedAct = query.list();

        transaction.commit();
        session.close();
        return passedAct;
    }

    public List<Activity> getDeniedAct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.actStat = 7 ";
        Query query = session.createQuery(hql);

        List<Activity> deniedAct = query.list();

        transaction.commit();
        session.close();
        return deniedAct;
    }

    public void passActivity(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Activity as a set a.actStat = 0 where actId = ?";
        Query query = session.createQuery(hql).setParameter(0, actId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public void denyActivity(int actId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Activity as a set a.actStat = 7 where actId = ?";
        Query query = session.createQuery(hql).setParameter(0, actId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public List<Actmem> getActMem() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Actmem as a";
        Query query = session.createQuery(hql);

        List<Actmem> result = query.list();

        transaction.commit();
        session.close();

        return result;
    }
}
