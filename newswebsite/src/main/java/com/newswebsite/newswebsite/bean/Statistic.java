package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Statistic {

    @Id
    private String date;
    private Integer news;
    private Integer user;
    public Statistic() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) {
        this.news = news;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Statistic(String date,Integer news,Integer user){
        this.date=date;
        this.news=news;
        this.user=user;
    }
}
