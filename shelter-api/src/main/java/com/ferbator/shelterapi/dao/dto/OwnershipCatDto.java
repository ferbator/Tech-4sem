package com.ferbator.shelterapi.dao.dto;

import com.ferbator.shelterapi.dao.entities.OwnershipCat;

import java.util.Objects;

public class OwnershipCatDto {
    private Long id;
    private Long ownerId;
    private Long catId;

    public OwnershipCatDto() {
    }

    public OwnershipCatDto(OwnershipCat ownershipCat) {
        this.ownerId = ownershipCat.getOwnerId();
        this.catId = ownershipCat.getCatId();
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
        OwnershipCatDto that = (OwnershipCatDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(ownerId, that.ownerId)
                && Objects.equals(catId, that.catId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, catId);
    }
}
