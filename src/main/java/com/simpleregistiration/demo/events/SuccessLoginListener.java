package com.simpleregistiration.demo.events;

import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.security.CustomPrincipal;
import com.simpleregistiration.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class SuccessLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(SuccessLoginListener.class);

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        User user = ((CustomPrincipal) authenticationSuccessEvent.getAuthentication().getPrincipal()).getUser();
        if (user.getFirstLoginDate() == null) {
            user.setFirstLoginDate(LocalDateTime.now());
            userService.update(user.getId(), user);
        }
    }
}
