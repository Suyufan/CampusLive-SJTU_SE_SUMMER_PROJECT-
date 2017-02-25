package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 昱凡 on 2016/9/9.
 */
@Entity
public class Frontpage {
    private int frontId;

    @Id
    @Column(name = "frontId")
    public int getFrontId() {
        return frontId;
    }

    public void setFrontId(int frontId) {
        this.frontId = frontId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frontpage frontpage = (Frontpage) o;

        if (frontId != frontpage.frontId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return frontId;
    }
}
