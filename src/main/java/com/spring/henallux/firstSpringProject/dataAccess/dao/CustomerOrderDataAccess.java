package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.CustomerOrder;
import com.spring.henallux.firstSpringProject.model.User;

import java.util.ArrayList;

public interface CustomerOrderDataAccess {

    Integer add(CustomerOrder order);

    void delete(Integer id);

    CustomerOrder get(Integer id);

    void update(CustomerOrder order);

    ArrayList<CustomerOrder> getAllOrders();


    ArrayList<CustomerOrder> getOrdersByUser(User user);
}
