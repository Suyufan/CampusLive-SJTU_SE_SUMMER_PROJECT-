package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by 昱凡 on 2016/7/8.
 */
@Entity
@IdClass(ActtagPK.class)
public class Acttag {
    private int actId;
    private int tagId;

    @Id
    @Column(name = "actId")
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Id
    @Column(name = "tagId")
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

        Acttag acttag = (Acttag) o;

        if (actId != acttag.actId) return false;
        if (tagId != acttag.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actId;
        result = 31 * result + tagId;
        return result;
    }
}
