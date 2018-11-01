package com.system.welcome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Welcome {

    @GetMapping("/index")
    public String welcome() {
        return "index";
    }
}
