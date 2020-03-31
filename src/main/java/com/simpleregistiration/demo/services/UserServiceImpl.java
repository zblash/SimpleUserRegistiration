package com.simpleregistiration.demo.services;

import com.simpleregistiration.demo.enums.RoleType;
import com.simpleregistiration.demo.errors.BadRequestException;
import com.simpleregistiration.demo.errors.ResourceNotFound;
import com.simpleregistiration.demo.models.Role;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public User findByActivationCode(String activationCode) {
       return userRepository.findByActivationCode(activationCode).orElseThrow(() -> new BadRequestException("Activation code is invalid"));
    }

    @Override
    public User findByResetToken(String resetToken) {
        return userRepository.findByPasswordResetToken(resetToken).orElseThrow(() -> new BadRequestException("Reset token is invalid"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User not found with email"));
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
        user.setPassword(updatedUser.getPassword());
        user.setActive(updatedUser.isActive());
        user.setBanned(updatedUser.isBanned());
        user.setPasswordResetToken(updatedUser.getPasswordResetToken());
        user.setActivationTokenSentTime(updatedUser.getActivationTokenSentTime());
        user.setActivationCode(updatedUser.getActivationCode());
        user.setRole(updatedUser.getRole());
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
