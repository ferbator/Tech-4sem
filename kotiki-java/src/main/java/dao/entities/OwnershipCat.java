package dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ownershipCats")
public class OwnershipCat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "owner_id", nullable = false)
    private long ownerId;
    @Basic
    @Column(name = "cat_id", nullable = false)
    private long catId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnershipCat that = (OwnershipCat) o;
        return id == that.id && ownerId == that.ownerId && catId == that.catId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, catId);
    }
}
