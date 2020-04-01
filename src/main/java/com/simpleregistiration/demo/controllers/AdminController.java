package com.simpleregistiration.demo.controllers;

import com.simpleregistiration.demo.enums.DateFilter;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/users/registered")
    public String getAllRegisteredUsers(@RequestParam(required = false) DateFilter date, Model model) {
        List<User> users;
        if (date != null) {
            long firstDate;
            switch (date) {
                case LASTDAY:
                    firstDate = 1000L * 60L * 60L * 24L;
                    break;
                case LASTWEEK:
                    firstDate = 1000L * 60L * 60L * 24L * 7L;
                    break;
                case LASTMONTH:
                    firstDate = 1000L * 60L * 60L * 24L * 30L;
                    break;
                default:
                    firstDate = 0;
                    break;
            }
            users = userService.findAllByRegisteredDateRange(new Date(System.currentTimeMillis() - firstDate), new Date());
        } else {
            users = userService.findAll();
        }
        model.addAttribute("onlineUsers",sessionRegistry.getAllPrincipals().size());
        model.addAttribute("users", users);
        return "admin/registered-users";
    }

    @GetMapping("/users/not-active")
    public String getAllNotActiveUsers(Model model) {
        Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        model.addAttribute("users", userService.findAllByActivateAndCodeSentTimeRange(false, yesterday, new Date()));
        return "admin/not-active-users";
    }
}
