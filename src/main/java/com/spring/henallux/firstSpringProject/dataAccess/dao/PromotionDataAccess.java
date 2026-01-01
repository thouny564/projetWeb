package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.Promotion;

import java.util.List;

public interface PromotionDataAccess {

    void add(Promotion promotion);

    void delete(Integer id);

    Promotion get(Integer id);

    void update(Promotion promotion);

    List<Promotion> getPromotions();

    List<Promotion> getPromotionsByProductId(Integer productId);
}
