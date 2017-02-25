package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 昱凡 on 2016/9/9.
 */
public class ActratePK implements Serializable {
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

        ActratePK actratePK = (ActratePK) o;

        if (userId != actratePK.userId) return false;
        if (actId != actratePK.actId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + actId;
        return result;
    }
}
