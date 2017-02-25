package Dao;

import Entity.Activity;
import Entity.Team;
import Entity.Teammem;
import Entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class TeamDao implements ITeamDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveTeam(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(team);

        transaction.commit();
        session.close();
    }

    public void joinTeam(int userId, int teamId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Teammem teammem = new Teammem();
        teammem.setTeamId(teamId);
        teammem.setUserId(userId);

        System.out.println(teamId);
        System.out.println(userId);

        session.save(teammem);

        transaction.commit();
        session.close();
    }

    public void leaveTeam(int userId, int teamId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Teammem teammem = new Teammem();
        teammem.setTeamId(teamId);
        teammem.setUserId(userId);

        session.delete(teammem);

        transaction.commit();
        session.close();
    }

    public List<Team> getCreatedTeam(int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Team as t where t.userId = ? ";
        Query query = session.createQuery(hql).setParameter(0, userId);

        List<Team> createdTeams = query.list();

        transaction.commit();
        session.close();
        return createdTeams;
    }

    public Team getTeamById(int teamId) {
        Session session = sessionFactory.openSession();

        Team team = (Team) session.get(Team.class, teamId);
        session.close();
        return team;
    }

    public Team getTeamByName(String teamName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Team as t where t.teamName = ?";

        Query query = session.createQuery(hql).setParameter(0, teamName);

        Team team = (Team) query.uniqueResult();

        transaction.commit();
        session.close();
        return team;
    }

    public void updateTeam(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Team as t set t.teamName = ?, t.teamDesc = ? where t.teamId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, team.getTeamName());
        query.setParameter(1, team.getTeamDesc());
        query.setParameter(2, team.getTeamId());

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public List<User> getTeamMem(int teamId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User as u where u.userId in (select t.userId from Teammem as t where t.teamId = ?)";
        Query query = session.createQuery(hql).setParameter(0, teamId);

        List<User> members = query.list();

        transaction.commit();
        session.close();
        return members;
    }

    public List<User> getAllUser(int teamId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User as u where u.username <> 'administrator' and u.userId not in (select t.userId from Teammem as t where t.teamId = ?)";
        Query query = session.createQuery(hql).setParameter(0, teamId);

        List<User> alluser = query.list();

        transaction.commit();
        session.close();
        return alluser;
    }

    public List<Team> searchTeam(String searchWord) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Team as t where t.teamName like ? ";
        Query query = session.createQuery(hql).setParameter(0, "%"+searchWord+"%");

        List<Team> result = query.list();

        transaction.commit();
        session.close();

        return result;
    }

    public List<Activity> getTeamAct(int teamId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Activity as a where a.groupId = ?";
        Query query = session.createQuery(hql).setParameter(0, teamId);

        List<Activity> result = query.list();

        transaction.commit();
        session.close();

        return result;
    }
}
