package com.meetme.web.controllers;

import com.meetme.models.bindingModels.UserRegisterBindingModel;
import com.meetme.services.PersonService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private final PersonService personService;

    public HomeController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/asd")
    public String asd() {
        return "asd";
    }
    @GetMapping("/")
    public String get() {
        return "index";
    }

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
    public String postRegister(/*@Valid*/ @ModelAttribute("registerModel") UserRegisterBindingModel userRegisterBindingModel,
                                          final BindingResult bindingResult) {
        UserRegisterBindingModel u =
                this.personService.registerUser(userRegisterBindingModel);
        return "redirect:/login";
    }

    private Model setModelAttributes(Model model, String obj, Object o) {
        return model.containsAttribute(obj) ? model : model.addAttribute(obj, o);
    }
}
