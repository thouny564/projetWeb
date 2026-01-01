package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderLineDAO implements OrderLineDataAccess {

    private final OrderLineRepository orderLineRepository;
    private final ProviderConverter converter;

    @Autowired
    public OrderLineDAO(OrderLineRepository orderLineRepository, ProviderConverter converter) {
        this.orderLineRepository = orderLineRepository;
        this.converter = converter;
    }

    @Override
    public void add(OrderLine orderLine) {
        orderLineRepository.save(converter.orderLineModelToEntity(orderLine));
    }

    @Override
    public void delete(Integer id) {
        orderLineRepository.deleteById(id);
    }

    @Override
    public OrderLine get(Integer id) {
        return orderLineRepository.findById(id)
                .map(converter::orderLineEntityToModel)
                .orElse(null);
    }

    @Override
    public void update(OrderLine orderLine) {
        orderLineRepository.save(converter.orderLineModelToEntity(orderLine));
    }


    public List<OrderLine> getOrdersLines() {
        List<OrderLineEntity> entities = orderLineRepository.findAll();
        List<OrderLine> lines = new ArrayList<>();
        for (OrderLineEntity e : entities) {
            lines.add(converter.orderLineEntityToModel(e));
        }
        return lines;
    }

    @Override
    public List<OrderLine> getOrderLinesByOrderId(Integer orderId) {
        List<OrderLineEntity> entities = orderLineRepository.findByOrderId(orderId);
        List<OrderLine> lines = new ArrayList<>();
        for (OrderLineEntity e : entities) {
            lines.add(converter.orderLineEntityToModel(e));
        }
        return lines;
    }




}
