package Entity;

import javax.persistence.*;

/**
 * Created by 昱凡 on 2016/7/20.
 */
@Entity
@IdClass(ActmemPK.class)
public class Actmem {
    private int userId;
    private int actId;
    private int score;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actmem actmem = (Actmem) o;

        if (userId != actmem.userId) return false;
        if (actId != actmem.actId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + actId;
        return result;
    }
}
