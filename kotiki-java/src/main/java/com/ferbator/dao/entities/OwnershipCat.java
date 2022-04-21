package com.ferbator.dao.entities;

import com.ferbator.dao.dto.OwnershipCatDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ownershipcats")
public class OwnershipCat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
    @Basic
    @Column(name = "cat_id", nullable = false)
    private Long catId;

    public OwnershipCat(OwnershipCatDTO ownershipCat) {
        this.ownerId = ownershipCat.getOwnerId();
        this.catId = ownershipCat.getCatId();
    }

    public OwnershipCat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnershipCat that = (OwnershipCat) o;
        return Objects.equals(id, that.id)
                && Objects.equals(ownerId, that.ownerId)
                && Objects.equals(catId, that.catId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, catId);
    }
}
