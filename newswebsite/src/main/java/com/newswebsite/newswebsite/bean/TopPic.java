package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopPic {
    @Id
    private Integer picID;
    private String pic;
    private String title;
    private String text;

    public TopPic(){}

    public Integer getPicID() {
        return picID;
    }

    public void setPicID(Integer picID) {
        this.picID = picID;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
