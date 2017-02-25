package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by 昱凡 on 2016/9/9.
 */
@Entity
@IdClass(ActratePK.class)
public class Actrate {
    private int userId;
    private int actId;
    private int score;
    private Calendar scoretime;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "actId")
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "scoretime")
    public Calendar getScoretime() {
        return scoretime;
    }

    public void setScoretime(Calendar scoretime) {
        this.scoretime = scoretime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actrate actrate = (Actrate) o;

        if (userId != actrate.userId) return false;
        if (actId != actrate.actId) return false;
        if (score != actrate.score) return false;
        if (scoretime != null ? !scoretime.equals(actrate.scoretime) : actrate.scoretime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + actId;
        result = 31 * result + score;
        result = 31 * result + (scoretime != null ? scoretime.hashCode() : 0);
        return result;
    }
}
