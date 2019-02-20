package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.Adm_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface Adm_recordRepository extends JpaRepository<Adm_record,Integer> {
    @Query(value = "select * from Adm_record order by time desc limit ?1, ?2", nativeQuery = true)
    List<Adm_record> page(int p1, int p2);

    @Query(value = "select * from Adm_record where admID = ?1", nativeQuery = true)
    List<Adm_record> queryByID(Integer id);

    @Query(value = "select * from Adm_record a where a.time = ?1", nativeQuery = true)
    List<Adm_record> findByTime(String time);

    @Query(value = "select * from Adm_record", nativeQuery = true)
    List<Adm_record> getAll();

    @Modifying
    @Transactional
    @Query(value = "insert into Adm_record(id, admID, time, record) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addAdmRecord(int id, int admID, String time, String record);

    @Query(value = "select count(*) from Adm_record", nativeQuery = true)
    long getColumn();
}
