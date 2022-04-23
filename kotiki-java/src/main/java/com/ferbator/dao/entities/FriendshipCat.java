package com.ferbator.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friendshipcats")
public class FriendshipCat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "first_cat_id", nullable = false)
    private Long firstCatId;
    @Basic
    @Column(name = "second_cat_id", nullable = false)
    private Long secondCatId;

    public FriendshipCat(FriendshipCat friendshipCatDTO) {
        this.firstCatId = friendshipCatDTO.getFirstCatId();
        this.secondCatId = friendshipCatDTO.getSecondCatId();
    }

    public FriendshipCat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstCatId() {
        return firstCatId;
    }

    public void setFirstCatId(Long firstCatId) {
        this.firstCatId = firstCatId;
    }

    public Long getSecondCatId() {
        return secondCatId;
    }

    public void setSecondCatId(Long secondCatId) {
        this.secondCatId = secondCatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipCat that = (FriendshipCat) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstCatId, that.firstCatId)
                && Objects.equals(secondCatId, that.secondCatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstCatId, secondCatId);
    }
}
