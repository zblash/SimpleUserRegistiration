package com.simpleregistiration.demo.controllers;

import com.simpleregistiration.demo.dtos.WritableActivation;
import com.simpleregistiration.demo.dtos.WritableForgotPassword;
import com.simpleregistiration.demo.dtos.WritableRegister;
import com.simpleregistiration.demo.events.OnUserForgotPasswordEvent;
import com.simpleregistiration.demo.events.OnUserRegistrationEvent;
import com.simpleregistiration.demo.models.User;
import com.simpleregistiration.demo.services.UserService;
import com.simpleregistiration.demo.utils.RandomStringGenerator;
import com.simpleregistiration.demo.utils.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("user", new WritableRegister());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid @ModelAttribute("user") WritableRegister register, BindingResult result, Model model, WebRequest request) {
        model.addAttribute("user", register);

        if (result.hasErrors()) {
            return "register";
        }
        User user = UserMapper.writableRegisterToUser(register);
        user.setActivationCode(RandomStringGenerator.generateNumberString());
        user.setActivationTokenSentTime(new Date());
        userService.create(user, register.getRoleType());
        eventPublisher.publishEvent(new OnUserRegistrationEvent(user, request.getContextPath(), request.getLocale()));

        return "redirect:/activation";
    }

    @GetMapping("/activation")
    public String activationPage(Model model) {
        model.addAttribute("activation", new WritableActivation());
        return "activation";
    }

    @PostMapping("/activation")
    public String activationPost(@Valid @ModelAttribute("activation") WritableActivation writableActivation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("activation", writableActivation);
            return "activation";
        }

        User user = userService.findByActivationCode(writableActivation.getActivationCode());
        user.setActive(true);

        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgot", new WritableForgotPassword());
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPasswordPost(@Valid @ModelAttribute("forgot") WritableForgotPassword writableForgotPassword, BindingResult result, Model model, WebRequest request) {

        if (result.hasErrors()) {
            model.addAttribute("forgot", writableForgotPassword);
            return "activation";
        }
        User user = userService.findByEmail(writableForgotPassword.getEmail());
        user.setPasswordResetToken(RandomStringGenerator.generateId());
        userService.update(user.getId(), user);
        eventPublisher.publishEvent(new OnUserForgotPasswordEvent(user, request.getContextPath(), request.getLocale()));
        return "redirect:/login";
    }
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
