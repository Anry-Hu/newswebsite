package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.UserRecord;
import com.newswebsite.newswebsite.repository.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRecordServiceImpl implements UserRecordService{
    @Autowired
    private UserRecordRepository userRecordRepository;

    @Override
    public List<UserRecord> findByName(String name){
        return userRecordRepository.findByName(name);
    }

    @Override
    public void addUserRecord(String name, Integer newsID, String news, String time){
        userRecordRepository.addUserRecord(name, newsID, news, time);
    }

    @Override
    public List<UserRecord> findByID(String name, String news){
        return userRecordRepository.findByID(name, news);
    }

    @Override
    public Integer getColumn(){
        return userRecordRepository.getColumn();
    }

    @Override
    public List<UserRecord> page(int p1, int p2){
        return userRecordRepository.page(p1, p2);
    }

    @Override
    public List<UserRecord> findByNewsID(Integer newsID){
        return userRecordRepository.findByNewsID(newsID);
    }

    @Override
    public List<UserRecord> findByTime(String time){
        return userRecordRepository.findByTime(time);
    }
}
