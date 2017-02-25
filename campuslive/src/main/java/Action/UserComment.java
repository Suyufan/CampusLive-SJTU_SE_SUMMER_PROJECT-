package Action;

import Dao.CommentDao;
import Entity.Usercomment;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 昱凡 on 2016/7/18.
 */
public class UserComment extends ActionSupport {
    private String commentWord;
    private int userId;
    private int actId;
    private String username;
    private Integer responseId;
    private int commentId;

    private CommentDao commentDao;

    public String saveComment(){
        System.out.println(commentWord);
        System.out.println(userId);
        System.out.println(responseId);
        System.out.println(actId);


        Usercomment comment = new Usercomment();
        comment.setCommentWord(commentWord);
        comment.setUserId(userId);
        comment.setActId(actId);
        comment.setUsername(username);
        if (responseId != null){
            comment.setResponseId(responseId);
        }
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        comment.setCommentTime(calendar);

        commentDao.saveComment(comment);

        return SUCCESS;
    }

    public String reportComment(){
        commentDao.reportComment(commentId);
        System.out.println(commentId);


        return SUCCESS;
    }

    public String getAllComment(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        List<Usercomment> allComment = commentDao.getAllComment();

        session.removeAttribute("allComment");
        session.setAttribute("allComment", allComment);

        return SUCCESS;
    }

    public String deleteComment(){
        Usercomment comment = commentDao.getCommentById(commentId);
        commentDao.deleteComment(comment);

        return SUCCESS;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public String getCommentWord() {
        return commentWord;
    }

    public void setCommentWord(String commentWord) {
        this.commentWord = commentWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }
}
