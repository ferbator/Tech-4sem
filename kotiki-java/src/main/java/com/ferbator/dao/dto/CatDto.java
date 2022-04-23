package com.ferbator.dao.dto;

import com.ferbator.dao.entities.Cat;
import com.ferbator.dao.enums.Colors;

import java.sql.Timestamp;
import java.util.Objects;

public class CatDto {
    private Long id;
    private Colors color;
    private String name;
    private String breed;
    private Timestamp birthday;

    public CatDto() {
    }

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.color = cat.getColor();
        this.breed = cat.getBreed();
        this.birthday = cat.getBirthday();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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
        CatDto catDTO = (CatDto) o;
        return Objects.equals(id, catDTO.id)
                && color == catDTO.color
                && Objects.equals(name, catDTO.name)
                && Objects.equals(breed, catDTO.breed)
                && Objects.equals(birthday, catDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, name, breed, birthday);
    }
}
