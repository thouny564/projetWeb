package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.repository.AuthorityRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorityDAO implements AuthorityDataAccess {

    private final AuthorityRepository authorityRepository;
    private final ProviderConverter converter;

    @Autowired
    public AuthorityDAO(AuthorityRepository authorityRepository, ProviderConverter converter) {
        this.authorityRepository = authorityRepository;
        this.converter = converter;
    }

    @Override
    public void add(Authority authority) {
        if (authority == null) return;
        authorityRepository.save(converter.authorityModelToAuthorityEntity(authority));
    }

    @Override
    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public Authority get(Integer id) {
        return authorityRepository.findById(id)
                .map(converter::authorityEntityToAuthorityModel)
                .orElse(null);
    }

    @Override
    public void update(Authority authority) {
        if (authority == null) return;
        authorityRepository.save(converter.authorityModelToAuthorityEntity(authority));
    }

    @Override
    public ArrayList<Authority> getAuthorities() {
        List<Authority> authorities = authorityRepository.findAll().stream()
                .map(converter::authorityEntityToAuthorityModel)
                .toList();
        return new ArrayList<>(authorities);
    }


}
