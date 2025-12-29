package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerOrderEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {


    List<CustomerOrderEntity> findByUser(UserEntity user);

    List<CustomerOrderEntity> findByPaid(Boolean paid);


    List<CustomerOrderEntity> findByStatus(String status);
}
