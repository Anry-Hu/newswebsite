package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Tags;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tags> page(int p1, int p2);

    long getColNum();

    void addTags(int tagID, String name);

    void updateTags(int tagID, String name);

    List<Tags> findByName(String name);

    List<Tags> queryByID(int id);

    void deleteTags(int tagID);

    Optional<Tags> findByID(int id);

    void updateDelete(int id);
}
