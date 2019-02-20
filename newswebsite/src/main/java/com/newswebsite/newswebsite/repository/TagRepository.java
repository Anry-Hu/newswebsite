package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Integer> {
    @Query(value = "select * from Tags a limit ?1, ?2", nativeQuery = true)
    List<Tags> page(int p1, int p2);

    @Modifying
    @Transactional
    @Query(value = "insert into Tags(tagID, name) value(?1, ?2)", nativeQuery = true)
    void addTags(int tagID, String name);

    @Modifying
    @Transactional
    @Query(value = "update Tags set name = ?2 where tagID = ?1", nativeQuery = true)
    void updateTags(int tagID, String name);

    @Modifying
    @Transactional
    @Query(value = "delete from Tags where tagID = ?1", nativeQuery = true)
    void deleteTags(int tagID);

    @Query(value = "select * from Tags where name = ?1", nativeQuery = true)
    List<Tags> findByName(String name);

    @Query(value = "select * from Tags where tagID = ?1", nativeQuery = true)
    List<Tags> queryByID(int id);

    @Modifying
    @Transactional
    @Query(value = "update Tags set tagID = tagID - 1 where tagID > ?1",nativeQuery = true)
    void updateDelete(int id);

    @Query(value = "select count(*) from Tags", nativeQuery = true)
    long getColumn();
}
