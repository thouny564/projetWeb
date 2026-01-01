package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerOrderEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CustomerOrderRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.CustomerOrder;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerOrderDAO implements CustomerOrderDataAccess {

    private final CustomerOrderRepository customerOrderRepository;
    private final ProviderConverter converter;

    @Autowired
    public CustomerOrderDAO(CustomerOrderRepository customerOrderRepository, ProviderConverter converter) {
        this.customerOrderRepository = customerOrderRepository;
        this.converter = converter;
    }

    @Override
    public Integer add(CustomerOrder order) {

        CustomerOrderEntity entity = converter.customerOrderModelToEntity(order);

        CustomerOrderEntity savedEntity = customerOrderRepository.save(entity);


        return savedEntity.getId();
    }


    @Override
    public void delete(Integer id) {
        customerOrderRepository.deleteById(id);
    }

    @Override
    public CustomerOrder get(Integer id) {
        return customerOrderRepository.findById(id)
                .map(converter::customerOrderEntityToModel)
                .orElse(null);
    }

    @Override
    public void update(CustomerOrder order) {

        CustomerOrderEntity entity = customerOrderRepository.findById(order.getId()).orElse(null);
        if (entity == null) return;


        entity.setStatus(order.getStatus());
        entity.setPaid(order.getPaid());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setOrderDate(order.getOrderDate());


        customerOrderRepository.save(entity);
    }


    @Override
    public ArrayList<CustomerOrder> getAllOrders() {
        List<CustomerOrderEntity> entities = customerOrderRepository.findAll();
        ArrayList<CustomerOrder> orders = new ArrayList<>();
        for (CustomerOrderEntity e : entities) {
            orders.add(converter.customerOrderEntityToModel(e));
        }
        return orders;
    }


    public ArrayList<CustomerOrder> getOrdersByUser(User user) {
        List<CustomerOrderEntity> entities = customerOrderRepository.findByUser(
                converter.userModelToUserEntity(user)
        );
        ArrayList<CustomerOrder> orders = new ArrayList<>();
        for (CustomerOrderEntity e : entities) {
            orders.add(converter.customerOrderEntityToModel(e));
        }
        return orders;
    }
}
