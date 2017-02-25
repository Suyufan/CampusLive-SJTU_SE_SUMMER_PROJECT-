package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 昱凡 on 2016/7/20.
 */
public class ActmemPK implements Serializable {
    private int userId;
    private int actId;

    @Column(name = "userId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "actId")
    @Id
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

        ActmemPK actmemPK = (ActmemPK) o;

        if (userId != actmemPK.userId) return false;
        if (actId != actmemPK.actId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + actId;
        return result;
    }
}
