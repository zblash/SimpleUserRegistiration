package com.simpleregistiration.demo.events;

import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener implements ApplicationListener<OnUserRegistrationEvent> {

    @Autowired
    MailUtil mailUtil;

    @Override
    public void onApplicationEvent(OnUserRegistrationEvent event) {
        User user = event.getUser();
        mailUtil.sendActivationMail(user.getEmail(), user.getActivationToken(), event.getApplicationUrl(), event.getLocale());
    }

}
