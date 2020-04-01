package com.simpleregistiration.demo.repositories;

import com.simpleregistiration.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByActivationCode(String activationCode);

    Optional<User> findByPasswordResetToken(String resetToken);

    List<User> findAllByCreatedAtBetween(Date firstDate, Date lastDate);

    List<User> findAllByActiveAndActivationTokenSentTimeBetween(boolean isActive, Date firstDate, Date lastDate);
}
