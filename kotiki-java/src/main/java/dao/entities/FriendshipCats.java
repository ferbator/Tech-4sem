package dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FriendshipCats {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "first_cat_id", nullable = false)
    private long firstCatId;
    @Basic
    @Column(name = "second_cat_id", nullable = false)
    private long secondCatId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFirstCatId() {
        return firstCatId;
    }

    public void setFirstCatId(long firstCatId) {
        this.firstCatId = firstCatId;
    }

    public long getSecondCatId() {
        return secondCatId;
    }

    public void setSecondCatId(long secondCatId) {
        this.secondCatId = secondCatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipCats that = (FriendshipCats) o;
        return id == that.id && firstCatId == that.firstCatId && secondCatId == that.secondCatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstCatId, secondCatId);
    }
}
