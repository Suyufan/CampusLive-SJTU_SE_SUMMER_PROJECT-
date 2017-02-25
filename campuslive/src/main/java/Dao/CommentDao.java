package Dao;

import Entity.Usercomment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public class CommentDao implements ICommentDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveComment(Usercomment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(comment);

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

    public void reportComment(int commentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Usercomment as u set u.reportTimes = u.reportTimes + 1 where u.commentId = ? ";

        Query query = session.createQuery(hql).setParameter(0, commentId);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public List<Usercomment> getAllComment() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Usercomment as u ";

        Query query = session.createQuery(hql);
        List<Usercomment> allComment = query.list();

        transaction.commit();
        session.close();
        return allComment;
    }

    public Usercomment getCommentById(int commentId) {
        Session session = sessionFactory.openSession();
        Usercomment comment =  (Usercomment) session.get(Usercomment.class, commentId);
        session.close();
        return comment;
    }

    public void deleteComment(Usercomment usercomment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(usercomment);

        transaction.commit();
        session.close();
    }
}
