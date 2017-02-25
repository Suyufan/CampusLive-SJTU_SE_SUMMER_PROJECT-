package Dao;

import Entity.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/20.
 */
public class MessageDao implements IMessageDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveMessage(Message message) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(message);

        transaction.commit();
        session.close();
    }

    public List<Message> getUnreadMessage(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Message as m where m.receiverName = ? and m.messageStat = 0 ";
        Query query = session.createQuery(hql).setParameter(0, username);

        List<Message> unreadMessages = query.list();

        transaction.commit();
        session.close();
        return unreadMessages;
    }

    public List<Message> getReadMessage(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Message as m where m.receiverName = ? and m.messageStat = 1 ";
        Query query = session.createQuery(hql).setParameter(0, username);

        List<Message> readMessages = query.list();

        transaction.commit();
        session.close();
        return readMessages;
    }

    public List<Message> getSendMessage(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Message as m where m.senderName = ? ";
        Query query = session.createQuery(hql).setParameter(0, username);

        List<Message> sendMessages = query.list();

        transaction.commit();
        session.close();
        return sendMessages;
    }

    public void setRead(int messageId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Message as m set m.messageStat = 1 where m.messageId = ? ";
        Query query = session.createQuery(hql).setParameter(0, messageId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public Long getReadMessageNumber(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select count(m.messageId) from Message as m where m.messageStat = 1 and m.receiverName = ? ";
        Query query = session.createQuery(hql).setParameter(0, username);

        List list = query.list();
        Long number = (Long) list.get(0);

        transaction.commit();
        session.close();

        return number;
    }

    public Long getUnreadMessageNumber(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select count(m.messageId) from Message as m where m.messageStat = 0 and m.receiverName = ?";
        Query query = session.createQuery(hql).setParameter(0, username);

        List list = query.list();
        Long number = (Long) list.get(0);

        transaction.commit();
        session.close();

        return number;
    }

    public Long getSendMessageNumber(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select count(m.messageId) from Message as m where m.senderName = ?";
        Query query = session.createQuery(hql).setParameter(0, username);

        List list = query.list();
        Long number = (Long) list.get(0);

        transaction.commit();
        session.close();

        return number;
    }
}
