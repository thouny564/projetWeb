package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.ProductRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductDAO implements ProductDataAccess {

    private final ProductRepository productRepository;
    private final ProviderConverter converter;

    @Autowired
    public ProductDAO(ProductRepository productRepository, ProviderConverter converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    @Override
    public void add(Product product) {
        productRepository.save(converter.productModelToProductEntity(product));
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product get(Integer id) {
        return productRepository.findById(id)
                .map(converter::productEntityToProductModel)
                .orElse(null);
    }

    @Override
    public void update(Product product) {
        productRepository.save(converter.productModelToProductEntity(product));
    }

    @Override
    public ArrayList<Product> getProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity e : entities) {
            products.add(converter.productEntityToProductModel(e));
        }
        return products;
    }
}
