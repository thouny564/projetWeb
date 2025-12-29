package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.Category;

import java.util.ArrayList;

public interface CategoryDataAccess {

    void add(Category category);

    void delete(Integer id);

    Category get(Integer id);

    void update(Category category);

    ArrayList<Category> getCategories();
}
