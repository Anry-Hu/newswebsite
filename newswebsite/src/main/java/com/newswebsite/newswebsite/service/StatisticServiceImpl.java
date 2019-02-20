package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Statistic;
import com.newswebsite.newswebsite.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public List<Statistic> getRecord(){
        return statisticRepository.getRecord();
    }
}
