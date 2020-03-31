package com.simpleregistiration.demo.controllers;

import com.simpleregistiration.demo.dtos.WritableRegister;
import com.simpleregistiration.demo.events.OnUserRegistrationEvent;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.services.UserService;
import com.simpleregistiration.demo.utils.RandomStringGenerator;
import com.simpleregistiration.demo.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("user", new WritableRegister());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid @ModelAttribute("register") WritableRegister register, BindingResult result, Model model, WebRequest request) {
        model.addAttribute("user", register);

        if (result.hasErrors()) {
            return "register";
        }
        User user = UserMapper.writableRegisterToUser(register);
        user.setActivationToken(RandomStringGenerator.generateNumberString());
        user.setActivationTokenSentTime(new Date());
        userService.create(user, register.getRoleType());
        eventPublisher.publishEvent(new OnUserRegistrationEvent(user, request.getContextPath(), request.getLocale()));

        return "redirect:/activation";
    }

    @GetMapping("activation")
    public String activationPage() {
        return "activation";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}