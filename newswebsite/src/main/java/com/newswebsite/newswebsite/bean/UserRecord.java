package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
//@Table(name = "userrecord")
@IdClass(URClass.class)
public class UserRecord {
    @Id
    private String name;
    @Id
    private Integer newsID;

    private String news;
    private String time;

    public UserRecord(){}

    public String getName(){
        return this.name;
    }

    public Integer getNewsID() {
        return this.newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString(){
        return "UserRecord{" +
                "name=" + name +
                ", newsID=" + newsID +
                ", news=" + news +
                ", time=" + time + '\'' +
                '}';
    }
}
