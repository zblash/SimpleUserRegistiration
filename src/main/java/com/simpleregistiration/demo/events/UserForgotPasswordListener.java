package com.simpleregistiration.demo.events;

import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserForgotPasswordListener implements ApplicationListener<OnUserForgotPasswordEvent> {

    @Autowired
    MailUtil mailUtil;

    @Override
    public void onApplicationEvent(OnUserForgotPasswordEvent event) {
        User user = event.getUser();
        mailUtil.sendPasswordResetMail(user.getEmail(), user.getPasswordResetToken(), event.getApplicationUrl(), event.getLocale());
    }

}