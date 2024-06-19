package com.rsa.bingo.app.infrastructure.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @GetMapping("/list")
    public String list() {
        return "/players/list";
    }

    @GetMapping("/view")
    public String view() {
        return "/players/view";
    }

    @GetMapping("/create")
    public String create() {
        return "/players/create";
    }
}
