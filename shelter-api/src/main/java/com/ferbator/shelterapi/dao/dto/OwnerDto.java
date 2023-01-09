package com.ferbator.shelterapi.dao.dto;

import com.ferbator.shelterapi.dao.entities.Owner;

import java.sql.Timestamp;
import java.util.Objects;

public class OwnerDto {
    private Long id;
    private String name;
    private Timestamp birthday;
    private String login;
    private String password;
    private String role;

    public OwnerDto(Owner own) {
        this.id = own.getId();
        this.name = own.getName();
        this.birthday = own.getBirthday();
        this.login = own.getLogin();
        this.password = own.getPassword();
        this.role = own.getRole();
    }

    public OwnerDto() {
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

    public void setName(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        OwnerDto ownerDto = (OwnerDto) o;
        return Objects.equals(id, ownerDto.id) && Objects.equals(name, ownerDto.name) && Objects.equals(birthday, ownerDto.birthday) && Objects.equals(login, ownerDto.login) && Objects.equals(password, ownerDto.password) && Objects.equals(role, ownerDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, login, password, role);
    }
}
