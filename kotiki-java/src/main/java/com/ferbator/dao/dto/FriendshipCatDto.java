package com.ferbator.dao.dto;

import com.ferbator.dao.entities.FriendshipCat;

import java.util.Objects;

public class FriendshipCatDto {
    private Long id;
    private Long firstCatId;
    private Long secondCatId;

    public FriendshipCatDto() {
    }

    public FriendshipCatDto(FriendshipCat friendshipCat) {
        this.id = friendshipCat.getId();
        this.firstCatId = friendshipCat.getFirstCatId();
        this.secondCatId = friendshipCat.getSecondCatId();
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
        FriendshipCatDto that = (FriendshipCatDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstCatId, that.firstCatId)
                && Objects.equals(secondCatId, that.secondCatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstCatId, secondCatId);
    }
}
