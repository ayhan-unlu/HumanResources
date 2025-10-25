package com.ayhanunlu.controller;

import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.dto.UserSessionDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // GET LOGIN
    // http://localhost:8080/login
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    // GET SIGNUP
    // http://localhost:8080/register
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    // GET LOGOUT
    // http://localhost:8080/logout
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }

    // POST SIGNUP
    // http://localhost:8080/register
    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto, Model model) {
        model.addAttribute("userDto", userDto);
        if (!userService.registerNewUser(userDto)) {
            model.addAttribute("errorMessage", "The username already in use");
            return "register";
        }
        model.addAttribute("successMessage", "User Registered successfully. Please go back to Login Page");
        return "register";
    }

    // POST LOGIN
    // http://localhost:8080/login
    @PostMapping("/login")
    public String login(@ModelAttribute UserDto userDto, HttpSession httpSession, Model model) {

        Integer loginAttempts = (Integer) httpSession.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0;
        }

        LoginResult loginResult = userService.login(userDto);

        switch (loginResult.getLoginResponse()) {
            case SUCCESS:
                httpSession.setAttribute("loginAttempts", 0);
                UserEntity userEntity = loginResult.getUserEntity();
                UserSessionDto userSessionDto = userService.prepareUserSessionDto(userEntity);
                httpSession.removeAttribute("userDto");
                httpSession.setAttribute("userSessionDto", userSessionDto);
                model.addAttribute("userSessionDto", userSessionDto);

                httpSession.setAttribute("userEntity", loginResult.getUserEntity());
                if (loginResult.getUserEntity().getRole().equals(Role.ADMIN)) {
                    return "admin_dashboard";
                } else {
                    return "employee_dashboard";
                }

            case NO_SUCH_USER:
                model.addAttribute("errorMessage", " No Such User. Please Try Again.");
                break;
            case BLOCKED:
                model.addAttribute("errorMessage", " User is Blocked. Please Contact Admin.");
                break;
            case FAIL:
                loginAttempts++;
                httpSession.setAttribute("loginAttempts", loginAttempts);
                if (loginAttempts >= 3) {
                    userService.setStatusBlocked(userDto);
                    model.addAttribute("errorMessage", "User is blocked. Cause of 3 wrong login attempts.");
                } else {
                    model.addAttribute("errorMessage", "Wrong Password. Please Try Again." + (3 - loginAttempts) + " attempts left");
                }break;
        }
        return "login";
    }
}
