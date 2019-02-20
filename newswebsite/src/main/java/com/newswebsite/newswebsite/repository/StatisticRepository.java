package com.newswebsite.newswebsite.repository;

import com.newswebsite.newswebsite.bean.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic,Integer> {
    @Query(value = "select * from Statistic order by date DESC limit 0,7  ", nativeQuery = true)
    List<Statistic> getRecord();
}
