package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Adm_record;
import com.newswebsite.newswebsite.repository.Adm_recordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Adm_recordServiceImpl implements Adm_recordService {
    @Autowired
    private Adm_recordRepository adm_recordRepository;

    @Override
    public List<Adm_record> page(int p1, int p2){
        return adm_recordRepository.page(p1, p2);
    }

    @Override
    public long getColNum(){
        return adm_recordRepository.getColumn();
    }

    @Override
    public List<Adm_record> findByTime(String time){
        return adm_recordRepository.findByTime(time);
    }

    @Override
    public List<Adm_record> queryByID(Integer id){
        return adm_recordRepository.queryByID(id);
    }

    @Override
    public void addAdmRecord(int id, int admID, String time, String record){
        adm_recordRepository.addAdmRecord(id, admID, time, record);
    }

    @Override
    public List<Adm_record> getAll(){
        return adm_recordRepository.getAll();
    }
}
