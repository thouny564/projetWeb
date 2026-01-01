package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.Authority;


import java.util.ArrayList;

public interface AuthorityDataAccess {
    void add(Authority authority);
    void delete(Integer id);
    Authority get(Integer id);
    void update(Authority authority);
    ArrayList<Authority> getAuthorities();
}
