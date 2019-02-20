package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.TopPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TopPicRepository extends JpaRepository<TopPic, Integer> {
    @Query(value = "select * from top_pic limit ?1, ?2", nativeQuery = true)
    List<TopPic> page(int p1, int p2);

    @Modifying
    @Transactional
    @Query(value = "insert into top_pic(picID, pic, title, text) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addTopPic(int picID, String pic, String title, String text);

    @Modifying
    @Transactional
    @Query(value = "update top_pic set pic = ?2, title = ?3, text = ?4 where picID = ?1", nativeQuery = true)
    void updateTopPic(int picID, String pic, String title, String text);

    @Query(value = "select * from top_pic where picID = ?1", nativeQuery = true)
    Optional<TopPic> findByID(Integer picID);

    @Query(value = "select count(*) from top_pic", nativeQuery = true)
    long getColumn();

    @Modifying
    @Transactional
    @Query(value = "update top_pic set picID = picID - 1 where picID > ?1", nativeQuery = true)
    void updateDelete(int id);

    @Modifying
    @Transactional
    @Query(value = "delete from top_pic where picID = ?1", nativeQuery = true)
    void deleteTopPic(int picID);

    @Query(value = "select * from top_pic where title = ?1", nativeQuery = true)
    List<TopPic> findByTitle(String title);

    @Query(value = "select * from top_pic where picID = ?1", nativeQuery = true)
    List<TopPic> queryByID(Integer picID);
}
