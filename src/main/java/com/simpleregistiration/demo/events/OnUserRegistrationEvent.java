package com.simpleregistiration.demo.events;

import com.simpleregistiration.demo.models.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnUserRegistrationEvent extends ApplicationEvent {

    private String applicationUrl;
    private User user;
    private Locale locale;

    public OnUserRegistrationEvent(User user, String applicationUrl, Locale locale) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.locale = locale;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
