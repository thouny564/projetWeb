package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.spring.henallux.firstSpringProject.dataAccess.entity.*;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.stereotype.Component;
import com.github.dozermapper.core.DozerBeanMapper;


@Component
public class ProviderConverter {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public UserEntity userModelToUserEntity(User user) {
        if (user == null) return null;
        UserEntity entity = mapper.map(user, UserEntity.class);
        entity.setBirthdate(user.getBirthdate());
        return entity;
    }


    public User userEntityToUserModel(UserEntity userEntity) {
        if (userEntity == null) return null;
        User user = mapper.map(userEntity, User.class);
        user.setBirthdate(userEntity.getBirthdate());
        return user;
    }





    public Authority authorityEntityToAuthorityModel(AuthorityEntity authorityEntity) {
        if (authorityEntity == null) return null;
        Authority model = mapper.map(authorityEntity, Authority.class);
        if (authorityEntity.getUser() != null) {
            model.setUser(userEntityToUserModel(authorityEntity.getUser()));
        }
        return model;
    }


    public AuthorityEntity authorityModelToAuthorityEntity(Authority authorityModel) {
        if (authorityModel == null) return null;
        AuthorityEntity entity = mapper.map(authorityModel, AuthorityEntity.class);

        if (authorityModel.getUser() != null) {
            entity.setUser(userModelToUserEntity(authorityModel.getUser()));
        }
        return entity;
    }




    public CategoryEntity categoryModelToCategoryEntity(Category category) {
        if (category == null) return null;
        return mapper.map(category, CategoryEntity.class);
    }

    public Category categoryEntityToCategoryModel(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return null;
        return mapper.map(categoryEntity, Category.class);
    }


    public ProductEntity productModelToProductEntity(Product product) {
        if (product == null) return null;
        ProductEntity entity = mapper.map(product, ProductEntity.class);
        entity.setCategory(categoryModelToCategoryEntity(product.getCategory()));
        return entity;
    }

    public Product productEntityToProductModel(ProductEntity productEntity) {
        if (productEntity == null) return null;
        Product model = mapper.map(productEntity, Product.class);
        model.setCategory(categoryEntityToCategoryModel(productEntity.getCategory()));
        return model;
    }


    public CustomerOrderEntity customerOrderModelToEntity(CustomerOrder model) {
        if (model == null) return null;

        CustomerOrderEntity entity = mapper.map(model, CustomerOrderEntity.class);
        entity.setUser(userModelToUserEntity(model.getUser()));

        return entity;
    }

    public CustomerOrder customerOrderEntityToModel(CustomerOrderEntity entity) {
        if (entity == null) return null;

        CustomerOrder model = mapper.map(entity, CustomerOrder.class);
        model.setUser(userEntityToUserModel(entity.getUser()));

        return model;
    }


    public OrderLineEntity orderLineModelToEntity(OrderLine model) {
        if (model == null) return null;

        OrderLineEntity entity = mapper.map(model, OrderLineEntity.class);
        entity.setOrder(customerOrderModelToEntity(model.getOrder()));
        entity.setProduct(productModelToProductEntity(model.getProduct()));

        return entity;
    }

    public OrderLine orderLineEntityToModel(OrderLineEntity entity) {
        if (entity == null) return null;

        OrderLine model = mapper.map(entity, OrderLine.class);
        model.setOrder(customerOrderEntityToModel(entity.getOrder()));
        model.setProduct(productEntityToProductModel(entity.getProduct()));

        return model;
    }







}
