package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by 昱凡 on 2016/7/15.
 */
@Entity
@IdClass(TeammemPK.class)
public class Teammem {
    private int teamId;
    private int userId;

    @Id
    @Column(name = "teamId")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Id
    @Column(name = "userId")
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

        Teammem teammem = (Teammem) o;

        if (teamId != teammem.teamId) return false;
        if (userId != teammem.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + userId;
        return result;
    }
}
