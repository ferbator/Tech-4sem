package com.ferbator.dao.dto;

import com.ferbator.dao.entities.OwnershipCat;

import java.util.Objects;

public class OwnershipCatDTO {
    private Long id;
    private Long ownerId;
    private Long catId;

    public OwnershipCatDTO(OwnershipCat ownershipCat) {
        this.ownerId = ownershipCat.getOwnerId();
        this.catId = ownershipCat.getCatId();
    }

    public OwnershipCatDTO() {
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
        OwnershipCatDTO that = (OwnershipCatDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(ownerId, that.ownerId)
                && Objects.equals(catId, that.catId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, catId);
    }
}
