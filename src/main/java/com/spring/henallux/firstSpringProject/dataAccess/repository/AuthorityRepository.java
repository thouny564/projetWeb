package com.spring.henallux.firstSpringProject.dataAccess.repository;


import com.spring.henallux.firstSpringProject.dataAccess.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
}
