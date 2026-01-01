package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.model.OrderLine;

import java.util.List;

public interface OrderLineDataAccess {

    void add(OrderLine orderLine);

    void delete(Integer id);

    OrderLine get(Integer id);

    void update(OrderLine orderLine);

    List<OrderLine> getOrdersLines();
    List<OrderLine> getOrderLinesByOrderId(Integer orderId);


}
