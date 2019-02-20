package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.URClass;
import com.newswebsite.newswebsite.bean.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRecordRepository extends JpaRepository<UserRecord, URClass> {
    @Query(value = "select * from UserRecord where name = ?1 order by time desc", nativeQuery = true)
    List<UserRecord> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into UserRecord(name, newsID, news, time) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addUserRecord(String name, Integer newsID, String news, String time);

    @Query(value = "select * from UserRecord where name = ?1 and news = ?2", nativeQuery = true)
    List<UserRecord> findByID(String name, String news);

    @Query(value = "select count(*) from UserRecord", nativeQuery = true)
    Integer getColumn();

    @Query(value = "select * from UserRecord order by time desc limit ?1, ?2", nativeQuery = true)
    List<UserRecord> page(int p1, int p2);

    @Query(value = "select * from UserRecord where newsID = ?1", nativeQuery = true)
    List<UserRecord> findByNewsID(Integer newsID);

    @Query(value = "select * from UserRecord where time = ?1", nativeQuery = true)
    List<UserRecord> findByTime(String time);
}
