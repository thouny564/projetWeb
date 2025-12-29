package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {

    void add(Product product);

    void delete(Integer id);

    Product get(Integer id);

    void update(Product product);

    ArrayList<Product> getProducts();
}
