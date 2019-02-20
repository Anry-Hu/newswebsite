package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.Administrator;
import com.newswebsite.newswebsite.bean.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "select * from News limit ?1, ?2", nativeQuery = true)
    List<News> page(int p1, int p2);

    @Modifying
    @Transactional
    @Query(value = "insert into News(newsID, time, title, summary, news_address, img_address, heat, tagID) value(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void addNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    @Modifying
    @Transactional
    @Query(value = "update News set time = ?2, title = ?3, summary = ?4, news_address = ?5, img_address = ?6, heat = ?7, tagID = ?8 where newsID = ?1", nativeQuery = true)
    void updateNews(int userID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID);

    @Modifying
    @Transactional
    @Query(value = "delete from News where newsID = ?1", nativeQuery = true)
    void deleteNews(int newsID);

    @Query(value = "select * from News where time = ?1", nativeQuery = true)
    List<News> findByTime(String time);

    @Query(value = "select * from News where title = ?1", nativeQuery = true)
    List<News> findByTitle(String title);

    @Query(value = "select * from News where tagID = ?1", nativeQuery = true)
    List<News> queryByID(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update News set newsID = newsID - 1 where newsID > ?1",nativeQuery = true)
    void updateDelete(int id);

    @Query(value = "select count(*) from News", nativeQuery = true)
    long getColumn();
}
