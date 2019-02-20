package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.TopPic;
import com.newswebsite.newswebsite.repository.TopPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopPicServiceImpl implements TopPicService{
    @Autowired
    private TopPicRepository topPicRepository;

    @Override
    public List<TopPic> getAll(){
        return topPicRepository.findAll();
    }

    @Override
    public List<TopPic> page(int p1, int p2){
        return topPicRepository.page(p1, p2);
    }

    @Override
    public long getColNum(){
        return topPicRepository.getColumn();
    }

    @Override
    public void addTopPic(int picID, String pic, String title, String text){
        topPicRepository.addTopPic(picID, pic, title, text);
    }

     @Override
    public void updateTopPic(int picID, String pic, String title, String text){
        topPicRepository.updateTopPic(picID, pic, title, text);
    }

    @Override
    public Optional<TopPic> findByID(Integer picID){
        return topPicRepository.findByID(picID);
    }

    @Override
    public void deleteTopPic(int picID){
        topPicRepository.deleteTopPic(picID);
    }

    @Override
    public void updateDelete(int id){
        topPicRepository.updateDelete(id);
    }

    @Override
    public List<TopPic> findByTitle(String title){
        return topPicRepository.findByTitle(title);
    }

    @Override
    public List<TopPic> queryByID(Integer picID){
        return topPicRepository.queryByID(picID);
    }
}
