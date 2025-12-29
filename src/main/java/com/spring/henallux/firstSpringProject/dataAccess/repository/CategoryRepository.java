package com.spring.henallux.firstSpringProject.dataAccess.repository;


import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
