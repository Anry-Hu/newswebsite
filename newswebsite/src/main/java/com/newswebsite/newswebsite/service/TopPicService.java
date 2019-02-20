package com.newswebsite.newswebsite.service;


import com.newswebsite.newswebsite.bean.TopPic;

import java.util.List;
import java.util.Optional;

public interface TopPicService {
    List<TopPic> getAll();

    List<TopPic> page(int p1, int p2);

    long getColNum();

    void addTopPic(int picID, String pic, String title, String text);

    void updateTopPic(int picID, String pic, String title, String text);

    Optional<TopPic> findByID(Integer picID);

    void deleteTopPic(int picID);

    void updateDelete(int id);

    List<TopPic> findByTitle(String title);

    List<TopPic> queryByID(Integer picID);
}
