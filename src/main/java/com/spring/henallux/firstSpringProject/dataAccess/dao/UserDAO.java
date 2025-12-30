package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.UserRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDAO implements UserDataAccess {

    private final UserRepository userRepository;
    private final ProviderConverter converter;

    @Autowired
    public UserDAO(UserRepository userRepository, ProviderConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public User getByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username);
        if (entity != null) {
            return converter.userEntityToUserModel(entity);
        }
        return null;
    }

    @Override
    public User getByMailAddress(String mailAddress) {
        UserEntity entity = userRepository.findByMailAddress(mailAddress);
        if (entity != null) {
            return converter.userEntityToUserModel(entity);
        }
        return null;
    }


    @Override
    public void add(User user) {
        if (getByUsername(user.getUsername()) == null) {
            userRepository.save(converter.userModelToUserEntity(user));
        } else {
            throw new IllegalArgumentException("Username already exists");
        }
    }


    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(Integer id) {
        return userRepository.findById(id)
                .map(converter::userEntityToUserModel)
                .orElse(null);
    }


    @Override
    public void update(User user) {
        userRepository.save(converter.userModelToUserEntity(user));
    }

    @Override
    public ArrayList<User> getUsers() {
        List<UserEntity> entities = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity e : entities) {
            users.add(converter.userEntityToUserModel(e));
        }
        return users;
    }

}
