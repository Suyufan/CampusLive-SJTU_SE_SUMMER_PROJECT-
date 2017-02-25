package Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 昱凡 on 2016/7/8.
 */
public class ActtagPK implements Serializable {
    private int actId;
    private int tagId;

    @Column(name = "actId")
    @Id
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Column(name = "tagId")
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActtagPK acttagPK = (ActtagPK) o;

        if (actId != acttagPK.actId) return false;
        if (tagId != acttagPK.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actId;
        result = 31 * result + tagId;
        return result;
    }
}
