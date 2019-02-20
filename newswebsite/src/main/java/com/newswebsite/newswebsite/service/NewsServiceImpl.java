package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.News;
import com.newswebsite.newswebsite.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> page(int p1, int p2){
        return newsRepository.page(p1, p2);
    }

    @Override
    public long getColNum(){
        return newsRepository.getColumn();
    }

    @Override
    public void addNews(int newsID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID){
        newsRepository.addNews(newsID, time, title, summary, news_address, img_address, heat, tagID);
    }

    @Override
    public void updateNews(int newsID, String time, String title, String summary, String news_address, String img_address, int heat, int tagID){
        newsRepository.updateNews(newsID, time, title, summary, news_address, img_address, heat, tagID);
    }

    @Override
    public List<News> findByTime(String time){
        return newsRepository.findByTime(time);
    }

    @Override
    public List<News> findByTitle(String title){
        return newsRepository.findByTitle(title);
    }

    @Override
    public List<News> queryByID(Integer id){
        return newsRepository.queryByID(id);
    }

    @Override
    public void deleteNews(int newsID){
        newsRepository.deleteNews(newsID);
    }

    @Override
    public Optional<News> findByID(Integer id) {
        return newsRepository.findById(id);
    }

    @Override
    public void updateDelete(int id){
        newsRepository.updateDelete(id);
    }
}
