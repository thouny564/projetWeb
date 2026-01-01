package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.PromotionEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.PromotionRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PromotionDAO implements PromotionDataAccess {

    private final PromotionRepository promotionRepository;
    private final ProviderConverter converter;

    @Autowired
    public PromotionDAO(PromotionRepository promotionRepository, ProviderConverter converter) {
        this.promotionRepository = promotionRepository;
        this.converter = converter;
    }

    @Override
    public void add(Promotion promotion) {
        promotionRepository.save(converter.promotionModelToEntity(promotion));
    }

    @Override
    public void delete(Integer id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Promotion get(Integer id) {
        return promotionRepository.findById(id)
                .map(converter::promotionEntityToModel)
                .orElse(null);
    }

    @Override
    public void update(Promotion promotion) {
        promotionRepository.save(converter.promotionModelToEntity(promotion));
    }

    @Override
    public List<Promotion> getPromotions() {
        List<PromotionEntity> entities = promotionRepository.findAll();
        List<Promotion> promotions = new ArrayList<>();
        for (PromotionEntity e : entities) {
            promotions.add(converter.promotionEntityToModel(e));
        }
        return promotions;
    }

    @Override
    public List<Promotion> getPromotionsByProductId(Integer productId) {
        List<PromotionEntity> entities = promotionRepository.findByProductId(productId);
        List<Promotion> promotions = new ArrayList<>();
        for (PromotionEntity e : entities) {
            promotions.add(converter.promotionEntityToModel(e));
        }
        return promotions;
    }
}
