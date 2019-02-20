package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Administrator {

    @Id
    private Integer admID;
    private String name;
    private String password;

    public Administrator() {
    }

    public Integer getAdmID() {
        return admID;
    }

    public void setAdmID(Integer admID) {
        this.admID = admID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Administrator(Integer admID, String name, String password) {
        this.admID = admID;
        this.name = name;
        this.password = password;
    }
}
