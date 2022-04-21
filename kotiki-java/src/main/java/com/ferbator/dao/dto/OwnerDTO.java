package com.ferbator.dao.dto;

import com.ferbator.dao.entities.Owner;

import java.sql.Timestamp;
import java.util.Objects;

public class OwnerDTO {
    private Long id;
    private String name;
    private Timestamp birthday;

    public OwnerDTO(Owner own) {
        this.id = own.getId();
        this.name = own.getName();
        this.birthday = own.getBirthday();
    }

    public OwnerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return Objects.equals(id, ownerDTO.id)
                && Objects.equals(name, ownerDTO.name)
                && Objects.equals(birthday, ownerDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday);
    }
}
