package com.simpleregistiration.demo.services;

import com.simpleregistiration.demo.enums.RoleType;
import com.simpleregistiration.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User create(User user, RoleType roleType);

    User update(Long id, User updatedUser);

    User changePassword(User user, String password);

    void delete(User user);

    boolean canRegister(User user);

}
