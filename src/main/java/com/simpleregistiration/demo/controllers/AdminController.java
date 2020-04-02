package com.simpleregistiration.demo.controllers;

import com.simpleregistiration.demo.enums.DateFilter;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public String getAllRegisteredUsersPage(@RequestParam(required = false) DateFilter date, Model model) {
        List<User> users;
        if (date != null) {
            LocalDateTime firstDate;
            switch (date) {
                case LASTDAY:
                    firstDate = LocalDateTime.now().minusDays(1);
                    break;
                case LASTWEEK:
                    firstDate = LocalDateTime.now().minusWeeks(1);
                    break;
                case LASTMONTH:
                    firstDate = LocalDateTime.now().minusMonths(1);
                    break;
                default:
                    firstDate = LocalDateTime.now();
                    break;
            }
            users = userService.findAllByRegisteredDateRange(firstDate, LocalDateTime.now());
        } else {
            users = userService.findAll();
        }
        model.addAttribute("onlineUsers",sessionRegistry.getAllPrincipals().size());
        model.addAttribute("users", users);
        return "admin/registered-users";
    }

    @GetMapping("/users/not-active")
    public String getAllNotActiveUsersPage(Model model) {
        model.addAttribute("users", userService.findAllByActivateAndCodeSentTimeRange(false, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        return "admin/not-active-users";
    }

    @GetMapping("/users/avg-complete-login")
    public String getAverageCompleteLoginPage(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, Model model) {

        int avgSec = userService.getCompleteLoginAverageByDateRange(date.atTime(23,59,59).minusDays(1), date.atTime(23,59,59), ChronoUnit.SECONDS);
        model.addAttribute("avgSec", avgSec);
        return "admin/avg-complete-login";
    }
}
