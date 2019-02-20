package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> page(int p1, int p2);

    long getColNum();

    void addNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    void updateNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    List<News> findByTime(String time);

    List<News> findByTitle(String title);

    List<News> queryByID(Integer id);

    void deleteNews(int newsID);

    Optional<News> findByID(Integer id);

    void updateDelete(int id);
}
