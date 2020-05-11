package com.edu.agh.fis.RentARoom.security.user.controller;

import com.edu.agh.fis.RentARoom.security.service.SecurityService;
import com.edu.agh.fis.RentARoom.security.user.model.Role;
import com.edu.agh.fis.RentARoom.security.user.model.User;
import com.edu.agh.fis.RentARoom.security.user.service.EmailService;
import com.edu.agh.fis.RentARoom.security.user.service.UserService;
import com.edu.agh.fis.RentARoom.security.user.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    private final static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/remind-passwd", method = RequestMethod.GET)
    public String remindPasswd(Model model) {
        model.addAttribute("userForm", new User());

        return "remind-passwd";
    }

    @RequestMapping(value = "/remind-passwd", method = RequestMethod.POST)
    public String remindPasswd(@ModelAttribute("userForm") User userForm) {

        String username = userForm.getUsername();
        String tempPassword = userService.setUserPassword(username);
        String email = userService.findByUsername(username).getEmail();
        emailService.sendTempPasswdMessage(email, username, tempPassword);
        log.info("Remind password email was sent");
        return "redirect:/";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userForm.setRole(Role.ADMIN.getAuthority());
        userService.save(userForm);
        log.info("First user of database saved");

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String accessDeny() {
        return "403";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> loggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return Collections.singletonMap("username", username);
    }
}