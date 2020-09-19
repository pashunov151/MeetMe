package com.meetme.web.controllers;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.models.serviceModels.UserServiceModel;
import com.meetme.services.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private final PersonService personService;

    public HomeController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String get(Model model) {
        //TODO: FIX LOGGING IN AFTER REGISTER
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User principal = (User) auth.getPrincipal();
            UserServiceModel existingUsersByEmail = this.personService.findExistingUsersByEmail(principal.getUsername());
            model.addAttribute("user", existingUsersByEmail);
        } catch (Exception e) {

        }
        return "index";
    }


    //TODO: For development purposes. It is going to be removed before being deployed.
//    @GetMapping("/asd")
//    public String asd(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User principal = (User) auth.getPrincipal();
//        UserServiceModel existingUsersByEmail = this.personService.findExistingUsersByEmail(principal.getUsername());
//        model.addAttribute("user", existingUsersByEmail);
//        return "asd";
//    }

    @GetMapping("/login")
    public String login(Model model) {
        model = this.setModelAttributes(model, "current", "login");
        return "login";
    }

    @PostMapping("/login-error")
    public ModelAndView onLoginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", email);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("registerModel") UserRegisterBindingModel userRegisterBindingModel, Model model) {
        this.setModelAttributes(model, "current", "register");
        this.setModelAttributes(model, "page", "reg");
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(/*@Valid*/@RequestParam("picture") MultipartFile file, @ModelAttribute("registerModel") UserRegisterBindingModel userRegisterBindingModel,
                                         final BindingResult bindingResult) {

        UserRegisterBindingModel u =
                this.personService.registerUser(userRegisterBindingModel, file);
        return "redirect:/login";
    }

    private Model setModelAttributes(Model model, String obj, Object o) {
        return model.containsAttribute(obj) ? model : model.addAttribute(obj, o);
    }
}
