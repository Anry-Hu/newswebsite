package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adm_record {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer admID;
    private String time;
    private String record;

    public Adm_record() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmID() {
        return admID;
    }

    public void setAdmID(Integer admID) {
        this.admID = admID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
