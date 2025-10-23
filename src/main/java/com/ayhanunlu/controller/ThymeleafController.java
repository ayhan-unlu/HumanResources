package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    @Autowired
    private UserService userService;

    // GET LOGIN
    // http://localhost:8080/login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // GET SIGNUP
    // http://localhost:8080/register
    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    // POST SIGNUP
    // http://localhost:8080/register
/*    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model){
        userService.registerNewUser(username,password);
        return "redirect:/login";
    }*/

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto, Model model){
        model.addAttribute("userDto",userDto);
        userService.registerNewUser(userDto);
        return "redirect:/login";
    }
}
