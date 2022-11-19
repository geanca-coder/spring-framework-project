package com.geanca.Springbootdemo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @Value(value = "${welcome.message}")
    private String welcomeMessage;

    @GetMapping
    public String helloWorld(){
        return welcomeMessage;
    }


}

