package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.User;

import java.util.ArrayList;

public interface UserDataAccess {
    void add(User user);
    void delete(Integer id);
    User get(Integer id);
    void update(User user);
    ArrayList<User> getUsers();

    User getByUsername(String username);
    User getByMailAddress(String  mailAddress);
}
