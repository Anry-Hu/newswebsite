package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.UserRecord;

import java.util.List;

public interface UserRecordService {
    List<UserRecord> findByName(String name);

    void addUserRecord(String name, Integer newsID, String news, String time);

    List<UserRecord> findByID(String name, String news);

    Integer getColumn();

    List<UserRecord> page(int p1, int p2);

    List<UserRecord> findByNewsID(Integer newsID);

    List<UserRecord> findByTime(String time);
}
