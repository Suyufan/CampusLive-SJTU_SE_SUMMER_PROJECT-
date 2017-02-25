package Dao;

import Entity.Usercomment;

import java.util.List;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public interface ICommentDao {
    public void saveComment(Usercomment comment);
    public List<Usercomment> getCommentByAct(int actId);
    public void reportComment(int commentId);

    public List<Usercomment> getAllComment();

    public Usercomment getCommentById(int commentId);

    public void deleteComment(Usercomment usercomment);
}
