package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Adm_record;
import java.util.List;
import java.util.Optional;

public interface Adm_recordService {
    long getColNum();

    List<Adm_record> page(int p1, int p2);

    List<Adm_record> queryByID(Integer id);

    List<Adm_record> findByTime(String time);

    void addAdmRecord(int id, int admID, String time, String record);

    List<Adm_record> getAll();
}
