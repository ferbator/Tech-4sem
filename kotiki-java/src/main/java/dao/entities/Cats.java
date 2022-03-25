package dao.entities;

import dao.enums.Colors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Cats {
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "birthday", nullable = true)
    private Timestamp birthday;
    @Basic
    @Column(name = "breed", nullable = true, length = -1)
    private String breed;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "color", nullable = true, length = -1)
    @Enumerated(EnumType.STRING)
    private Colors color;

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cats cats = (Cats) o;
        return id == cats.id && Objects.equals(name, cats.name) && Objects.equals(birthday, cats.birthday) && Objects.equals(breed, cats.breed) && Objects.equals(color, cats.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, breed, id, color);
    }
}
