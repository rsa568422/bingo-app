package com.rsa.bingo.app.infrastructure.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping({"", "/", "/home", "home/"})
    public String home() {
        return "redirect:/player";
    }
}
