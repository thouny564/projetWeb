package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CategoryRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryDAO implements CategoryDataAccess {

    private final CategoryRepository categoryRepository;
    private final ProviderConverter converter;

    @Autowired
    public CategoryDAO(CategoryRepository categoryRepository, ProviderConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    @Override
    public void add(Category category) {
        categoryRepository.save(converter.categoryModelToCategoryEntity(category));
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category get(Integer id) {
        return categoryRepository.findById(id)
                .map(converter::categoryEntityToCategoryModel)
                .orElse(null);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(converter.categoryModelToCategoryEntity(category));
    }

    @Override
    public ArrayList<Category> getCategories() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        ArrayList<Category> categories = new ArrayList<>();
        for (CategoryEntity e : entities) {
            categories.add(converter.categoryEntityToCategoryModel(e));
        }
        return categories;
    }
}
