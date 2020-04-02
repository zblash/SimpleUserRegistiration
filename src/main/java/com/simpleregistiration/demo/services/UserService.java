package com.simpleregistiration.demo.services;

import com.simpleregistiration.demo.enums.RoleType;
import com.simpleregistiration.demo.models.User;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findAllByRegisteredDateRange(LocalDateTime firstDate, LocalDateTime lastDate);

    List<User> findAllByActivateAndCodeSentTimeRange(boolean isActive, LocalDateTime firstDate, LocalDateTime lastDate);

    User findById(Long id);

    User findByActivationCode(String activationCode);

    User findByResetToken(String resetToken);

    User findByEmail(String email);

    User create(User user, RoleType roleType);

    User update(Long id, User updatedUser);

    User changePassword(User user, String password);

    void delete(User user);

    boolean canRegister(User user);

    int getCompleteLoginAverageByDateRange(LocalDateTime firstDate, LocalDateTime lastDate, TemporalUnit temporalUnit);

}
