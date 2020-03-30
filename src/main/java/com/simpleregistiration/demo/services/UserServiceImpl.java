package com.simpleregistiration.demo.services;

import com.simpleregistiration.demo.enums.RoleType;
import com.simpleregistiration.demo.errors.BadRequestException;
import com.simpleregistiration.demo.models.Role;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public User create(User user, RoleType roleType) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.createOrFind("ROLE_"+roleType.toString());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User updatedUser) {
        User user = findById(id);
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        return update(user.getId(),user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean canRegister(User user){
        return !userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
