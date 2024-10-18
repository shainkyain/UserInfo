package com.devil.Spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/home")
    public String hello(){
        return "Kar ra hai kam";
    }
}
