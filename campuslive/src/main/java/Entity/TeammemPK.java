package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 昱凡 on 2016/7/15.
 */
public class TeammemPK implements Serializable {
    private int teamId;
    private int userId;

    @Column(name = "teamId")
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name = "userId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeammemPK teammemPK = (TeammemPK) o;

        if (teamId != teammemPK.teamId) return false;
        if (userId != teammemPK.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + userId;
        return result;
    }
}
