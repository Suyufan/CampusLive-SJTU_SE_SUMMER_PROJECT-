package Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/7/18.
 */
@Entity
public class Usercomment {
    private int commentId;
    private String commentWord;
    private Calendar commentTime;
    private int userId;
    private String username;
    private int actId;
    private Integer responseId;
    private int reportTimes;

    @Id
    @Column(name = "commentId")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "commentWord")
    public String getCommentWord() {
        return commentWord;
    }

    public void setCommentWord(String commentWord) {
        this.commentWord = commentWord;
    }

    @Basic
    @Column(name = "commentTime")
    public Calendar getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Calendar commentTime) {
        this.commentTime = commentTime;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "actId")
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "responseId")
    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    @Basic
    @Column(name = "reportTimes")
    public int getReportTimes() {
        return reportTimes;
    }

    public void setReportTimes(int reportTimes) {
        this.reportTimes = reportTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usercomment that = (Usercomment) o;

        if (commentId != that.commentId) return false;
        if (userId != that.userId) return false;
        if (actId != that.actId) return false;
        if (reportTimes != that.reportTimes) return false;
        if (commentWord != null ? !commentWord.equals(that.commentWord) : that.commentWord != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (responseId != null ? !responseId.equals(that.responseId) : that.responseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (commentWord != null ? commentWord.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + actId;
        result = 31 * result + (responseId != null ? responseId.hashCode() : 0);
        result = 31 * result + reportTimes;
        return result;
    }
}
