package com.newswebsite.newswebsite.service;

import com.newswebsite.newswebsite.bean.Administrator;
import com.newswebsite.newswebsite.bean.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    long getColNum();

    void addUser(int userID, String name, String phone, String password);

    void updateUser(int userID, String name, String phone, String password);

    Optional<User> findByID(Integer id);

    void deleteUser(int userID);

    List<User> findByName(String name);

    List<User> findByPhone(String phone);

    List<User> queryByID(Integer id);

    List<User> page(int p1, int p2);

    List<Administrator> login(String name, String password);

    void updateDelete(int id);
}
