package com.newswebsite.newswebsite.bean;

import java.io.Serializable;
import java.util.Objects;

public class URClass implements Serializable {
    private String name;
    private Integer newsID;

    public URClass(){}

    public URClass(String name, Integer newsID){
        this.name = name;
        this.newsID = newsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URClass)) return false;
        URClass urClass = (URClass) o;
        return Objects.equals(getName(), urClass.getName()) &&
                Objects.equals(getNewsID(), urClass.getNewsID());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getNewsID());
    }
}
