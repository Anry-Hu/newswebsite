package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Tags;
import com.newswebsite.newswebsite.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tags> page(int p1, int p2){
        return tagRepository.page(p1, p2);
    }

    @Override
    public long getColNum(){
        return tagRepository.getColumn();
    }

    @Override
    public void addTags(int tagID, String name){
        tagRepository.addTags(tagID, name);
    }

    @Override
    public void updateTags(int tagID, String name){
        tagRepository.updateTags(tagID, name);
    }

    @Override
    public List<Tags> findByName(String name){
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tags> queryByID(int id){
        return tagRepository.queryByID(id);
    }

    @Override
    public void deleteTags(int tagID){
        tagRepository.deleteTags(tagID);
    }

    @Override
    public Optional<Tags> findByID(int id) {
        return tagRepository.findById(id);
    }

    @Override
    public void updateDelete(int id){
        tagRepository.updateDelete(id);
    }
}
