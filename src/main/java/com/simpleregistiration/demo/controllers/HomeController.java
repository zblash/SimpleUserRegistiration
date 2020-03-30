package com.simpleregistiration.demo.controllers;

import com.simpleregistiration.demo.dtos.WritableRegister;
import com.simpleregistiration.demo.services.UserService;
import com.simpleregistiration.demo.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String Register(Model model){
        model.addAttribute("user",new WritableRegister());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid @ModelAttribute("register") WritableRegister register, BindingResult result, Model model){
        model.addAttribute("user", register);

        if (result.hasErrors()){
            return "register";
        } else {
            userService.create(UserMapper.writableRegisterToUser(register), register.getRoleType());

        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
}